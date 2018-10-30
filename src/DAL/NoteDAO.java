package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class NoteDAO implements DAO<NoteDTO> {

    private static Connection connection = ConnectToDB.connect();

    public void addJointNote(NoteDTO noteDTO, ArrayList<String> usersIds) throws SQLException {
        this.add(noteDTO);
        String sql = "INSERT INTO jointNotes(noteId, userId) VALUES(?,?)";
        String update = "update notes set isJoint = 1 where id = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(update)) {
            updateStatement.setString(1, noteDTO.getId());
            updateStatement.executeUpdate();
            PreparedStatement insertStatement = connection.prepareStatement(sql);
            for (String userId : usersIds) {
                insertStatement.setString(1, noteDTO.getId());
                insertStatement.setString(2, userId);
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(NoteDTO noteDTO) throws SQLException {
        String sql = "INSERT INTO notes(id,userId,content,year, month, day) VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, noteDTO.getId());
        preparedStatement.setString(2, noteDTO.getUserId());
        preparedStatement.setString(3, noteDTO.getContent());
        preparedStatement.setString(4, Integer.toString(noteDTO.getYear()));
        preparedStatement.setString(5, Integer.toString(noteDTO.getMonth()));
        preparedStatement.setString(6, Integer.toString(noteDTO.getDay()));
        preparedStatement.executeUpdate();
    }

    @Override
    public NoteDTO get(String id) throws SQLException {
        String sql = "SELECT userId, content, isJoint, year, month, day, isDone FROM notes WHERE  id = ?";
        PreparedStatement selectNote = connection.prepareStatement(sql);
        selectNote.setString(1, id);
        ResultSet rs = selectNote.executeQuery();
        if (rs.next()) return new NoteDTO(id,
                rs.getString("userId"),
                rs.getString("content"),
                rs.getInt("year"),
                rs.getInt("month"),
                rs.getInt("day"),
                rs.getInt("isJoint"),
                rs.getInt("isDone")
        );
        return null;
    }

    @Override
    public void update(NoteDTO noteDTO, String... args) {
    } //???

    @Override
    public void delete(NoteDTO noteDTO) throws SQLException {
        String sql = "DELETE FROM notes WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, noteDTO.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList<NoteDTO> getAll() throws SQLException {
        ArrayList<NoteDTO> allNotes = new ArrayList<>();
        String sql = "SELECT id, userId, content, isJoint, year, month, day, isDone FROM notes";
        PreparedStatement selectNotes = connection.prepareStatement(sql);
        ResultSet rs = selectNotes.executeQuery();
        while (rs.next()) {
            allNotes.add(new NoteDTO(rs.getString("id"),
                    rs.getString("userId"),
                    rs.getString("content"),
                    rs.getInt("year"),
                    rs.getInt("month"),
                    rs.getInt("day"),
                    rs.getInt("isJoint"),
                    rs.getInt("isDone")));
        }
        return allNotes;
    }

    @Override
    public ArrayList<NoteDTO> getSome(Predicate<NoteDTO> predicate) throws SQLException{
        ArrayList<NoteDTO> some = new ArrayList<>();
        for(NoteDTO noteDTO: this.getAll()){
            if(predicate.test(noteDTO)){
                some.add(noteDTO);
            }
        }
        return some;
    }

}