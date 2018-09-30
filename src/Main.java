import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) {

       PrintStream stream = null;
        try {
            stream = new PrintStream(new File("D:\\JavaPrioects\\TODO_list\\src\\test.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        User alice = new User("Alice","password");
        User bob = new User("Bob","password");
        alice.addNote(new NoteDate(), "Make a tea");
        alice.addNote(new NoteDate(),"Push to GitHub");
        alice.addNote(new NoteDate(2017,8,26), "Note2");
        alice.addNote(new JointNote(new NoteDate(),"Have a meal", alice));
        alice.printNotes(System.out);

        /*stream.println("\n");

        ToDoList todOlist = new ToDoList();
        todOlist.addNote(2017,8,26, "Note2");
        todOlist.addNote(2019,1,1,"Celebrate New Year.");
        todOlist.addNote(2019,1,2,"Celebrate New Year TWICE.");
        todOlist.markAsDone(2);
        todOlist.addNote(2021,1,2,"Celebrate New Year ONE MORE TIME.");
        todOlist.printAll(stream);*/
        //todOlist.printNotesInTheInerval(new NoteDate(2018,1,1),new NoteDate(2020,1,1),System.out);

     // todOlist.removeNotesFromInerval(new NoteDate(2017,9,20),new NoteDate(2019,6,20)); //deleting

   //    todOlist.printAll();


      /* todOlist.addNote(2021,8,25,"Graduate from university!");
       todOlist.addNote("Push everything to GotHub.");
       todOlist.showAll();
       System.out.print("\n");
       todOlist.changecontent(2018,8,16,"Commit to local repository then push to GitHub!");
       todOlist.addNote(2021,8,25, "Have a good time.");
       todOlist.showAll();*/
    }
}
