import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;

class Task extends JPanel {

    JLabel Index;
    JTextField task_Name, Description;
    JButton Done;

    Color graylight = new Color(192, 192, 192);
    Color white = new Color(255, 255, 255);
    Color doneColor = new Color(255, 255, 255);
    Border emptyBorder = BorderFactory.createEmptyBorder();

    private boolean checked;

    Task() {
        this.setPreferredSize(new Dimension(400, 50)); // set size of task
        this.setBackground(graylight); // set background color of task

        this.setLayout(new BorderLayout()); // set layout of task

        checked = false;

        Index = new JLabel("Task: "); // create index label
        Index.setPreferredSize(new Dimension(50, 20)); // set size of index label
        Index.setHorizontalAlignment(JLabel.CENTER); // set alignment of index label
        this.add(Index, BorderLayout.WEST); // add index label to task

        Description = new JTextField(" Description & Due Date: "); // create description label
        Description.setBackground(graylight);// set background color of text field
        Description.setBorder(emptyBorder);
        Description.setPreferredSize(new Dimension(50, 20)); // set size of description label

        this.add(Description, BorderLayout.SOUTH); // add description label to task

        task_Name = new JTextField("Task..."); // create task name text field
        task_Name.setBorder(BorderFactory.createEmptyBorder()); // remove border of text field
        task_Name.setBackground(graylight); // set background color of text field
        task_Name.setPreferredSize(getPreferredSize());

        this.add(task_Name, BorderLayout.CENTER);

        Done = new JButton("Done");
        Done.setPreferredSize(new Dimension(100, 40));
        Done.setBorder(BorderFactory.createEmptyBorder());
        Done.setBackground(doneColor);
        Done.setFocusPainted(false);

        this.add(Done, BorderLayout.EAST);

    }

    public void changeIndex(int num) {
        this.Index.setText(num + ""); // num to String
        this.revalidate(); // refresh
    }

    public JButton getDone() {
        return Done;
    }

    public boolean getState() {
        return checked;
    }

    public void changeState() {
        this.setBackground(white);
        task_Name.setBackground(white);
        Description.setBackground(white);
        checked = true;
        revalidate();
    }
}

class List extends JPanel {

    Color lightColor = new Color(204, 204, 204);

    List() {

        GridLayout layout = new GridLayout(10, 1);
        layout.setVgap(5); // Vertical gap

        this.setLayout(layout); // 10 tasks
        this.setPreferredSize(new Dimension(400, 560));
        this.setBackground(lightColor);
    }

    public void updateNumbers() {
        Component[] listItems = this.getComponents();

        for (int i = 0; i < listItems.length; i++) {
            if (listItems[i] instanceof Task) {
                ((Task) listItems[i]).changeIndex(i + 1);
            }
        }

    }

    public void removeCompletedTasks() {

        for (Component c : getComponents()) {
            if (c instanceof Task) {
                if (((Task) c).getState()) {
                    remove(c); // remove the component
                    updateNumbers(); // update the indexing of all items
                }
            }
        }

    }
}

class Footer extends JPanel {

    JButton addTask;
    JButton clear;

    Color white = new Color(255, 255, 255);
    Color lightColor = new Color(204, 204, 204);

    Footer() {
        this.setPreferredSize(new Dimension(400, 60));
        this.setBackground(lightColor);

        addTask = new JButton("Add Task"); // add task button
        addTask.setFont(new Font("Sans-serif", Font.ITALIC, 20)); // set font
        addTask.setVerticalAlignment(JButton.BOTTOM); // align text to bottom
        addTask.setBackground(white); // set background color
        this.add(addTask); // add to footer

        this.add(Box.createHorizontalStrut(20)); // Space between buttons

        clear = new JButton("Clear finished tasks"); // clear button
        clear.setFont(new Font("Sans-serif", Font.ITALIC, 20)); // set font
        clear.setBackground(white); // set background color
        this.add(clear); // add to footer
    }

    public JButton getNewTask() {
        return addTask;
    }

    public JButton getClear() {
        return clear;
    }
}

class TitleBar extends JPanel {

    Color lightColor = new Color(204, 204, 204);

    TitleBar() {
        this.setPreferredSize(new Dimension(400, 80)); // Size of the title bar
        this.setBackground(lightColor); // Color of the title bar
        JLabel titleText = new JLabel("To Do List"); // Text of the title bar
        titleText.setPreferredSize(new Dimension(200, 60)); // Size of the text
        titleText.setFont(new Font("Sans-serif", Font.BOLD, 20)); // Font of the text
        titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
        this.add(titleText); // Add the text to the title bar
    }
}

class AppFrame extends JFrame {

    private TitleBar title;
    private Footer footer;
    private List list;

    private JButton newTask;
    private JButton clear;

    AppFrame() {
        this.setSize(400, 600); // 400 width and 600 height
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true); // Make visible

        title = new TitleBar();
        footer = new Footer();
        list = new List();

        this.add(title, BorderLayout.NORTH); // Add title bar on top of the screen
        this.add(footer, BorderLayout.SOUTH); // Add footer on bottom of the screen
        this.add(list, BorderLayout.CENTER); // Add list in middle of footer and title

        newTask = footer.getNewTask();
        clear = footer.getClear();

        addListeners();
    }

    public void addListeners() {
        newTask.addMouseListener(new MouseAdapter() {
            @override
            public void mousePressed(MouseEvent e) {
                Task task = new Task();
                list.add(task); // Add new task to list
                list.updateNumbers(); // Updates the numbers of the tasks

                task.getDone().addMouseListener(new MouseAdapter() {
                    @override
                    public void mousePressed(MouseEvent e) {

                        task.changeState(); // Change color of task
                        list.updateNumbers(); // Updates the numbers of the tasks
                        revalidate(); // Updates the frame

                    }
                });
            }

        });

        clear.addMouseListener(new MouseAdapter() {
            @override
            public void mousePressed(MouseEvent e) {
                list.removeCompletedTasks(); // Removes all tasks that are done
                repaint(); // Repaints the list
            }
        });
    }

}

public class ToDoList1 {

    public static void main(String args[]) {
        AppFrame frame = new AppFrame(); // Create the frame
    }
}

@interface override {

}