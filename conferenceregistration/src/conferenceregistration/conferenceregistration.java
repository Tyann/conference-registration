/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conferenceregistration;

/**
 *
 * @author YannErv
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 


public class conferenceregistration extends JFrame
{
   //Variables for the description
   private JLabel description;
  
   //Variables for registration type
   public final double GENERAL = 895.0; //$895 for the general attendant
   public final double STUDENT = 495.0; //$495 for the student attendant
  
   private JRadioButton general;
   private JRadioButton student;
   private ButtonGroup attGroup;  //radio button group for type of attendant
  
   //Variables for optional dinner and speech attendance
   public final double DINNER = 30.0; //$30 per person cost for the dinner & speeach
  
   private JCheckBox dinnerSpeech;
  
    
 
   private JList workshops;
   private JList selectedWorkshops;
   private static final String[] seminars = {"Introduction to E-commerce", "The Future of the Web",
                                "Advanced Java Programming", "Network Security"};
   private static final double[] seminar_cost = {295.0, 295.0, 395.0, 395.0};    //costs corresponding seminars                       
   //Variables for the buttons
   private JButton calcButton;   // To calculate the total cost
   private JButton clearButton;  //To clear the user selections
   private JButton exitButton;   // To exit the application
  
   //Create the panels
   private JPanel workshopsPanel=new JPanel();
   private JPanel descriptPanel= new JPanel();
   private JPanel attPanel=new JPanel();
   private JPanel dinnerPanel=new JPanel();
   private JPanel buttonPanel=new JPanel();
  
   /**
      Constructor
   */
   public conferenceregistration()
   {
      //set the title
      setTitle("Conference Registration System");
     
      //Specify an action for the close button
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
      //add layout manager
      setLayout(new BorderLayout());
     
      //build panels
      buildDescriptPanel();
      buildAttPanel();
      buildDinnerPanel();
      buildWorkshopsPanel();
      buildButtonPanel();
     
      //add the panels to the content pane
      add(descriptPanel, BorderLayout.NORTH);
      add(buttonPanel, BorderLayout.SOUTH);
      add(workshopsPanel, BorderLayout.EAST);
      add(attPanel, BorderLayout.WEST);
      add(dinnerPanel, BorderLayout.CENTER);
     
      //pack and display
      pack();
      setVisible(true);
   }
  
   /**
      buildDescriptPanel() will create and display a description of the window.  
   */
   private void buildDescriptPanel()
   {
      //create the label
      description = new JLabel("Select Registration Options");
     
      //add layout manager
      descriptPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
     
      //add label to the panel
      descriptPanel.add(description);
   }
  
   /**
      buildAttPanel will create and display a radio button option for the
      user to choose if they're a student or general attendant.
   */
   private void buildAttPanel()
   {
      //set window size
      attPanel.setSize(50, 50);
     
      //Create radio buttons
      general = new JRadioButton("General Registration", true);
      student = new JRadioButton("Student Registration");
     
      //Group radio buttons
      attGroup = new ButtonGroup();
      attGroup.add(general);
      attGroup.add(student);
     
      //create layout manager
      attPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
     
      //create a border
      attPanel.setBorder(BorderFactory.createTitledBorder("Registration Type"));
     
      //add radio buttons to the panel
      attPanel.add(general);
      attPanel.add(student);
   }
  
    /**
      The getAttCost method returns the cost for the selected attendant registration.
      @return attCost One of the constants GENERAL or STUDENT will be returned.
   */

   public double getAttCost()
   {
      double attCost = 0.0;

      if (general.isSelected())
         attCost = GENERAL;
      else if (student.isSelected())
         attCost = STUDENT;

      return attCost;
   }
  
   /**
      This class will create and display a checkbox option for the
      user to choose if they want to attend an optional dinner and keynote speech.  
   */
   private void buildDinnerPanel()
   {
      //create checkbox
      dinnerSpeech = new JCheckBox("Optional Dinner and Keynote Speech", false);
     
      //create a layout manager
      dinnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
     
      //add checkbox to the panel
      dinnerPanel.add(dinnerSpeech);
   }
  
   /**
      The getDSCost method returns the cost for the dinner and keynote speech.
      @return attCost One of the constants GENERAL or STUDENT will be returned.
   */

   public double getDSCost()
   {
      double keynoteCost = 0.0;

      if (dinnerSpeech.isSelected())
         keynoteCost = DINNER;
      else
         keynoteCost = 0.0;

      return keynoteCost;
   }
  
  
   /**
      buildWorkshopsPanel create and display a list of workshops for the
      user to choose as many as they want and in any order.
   */
   private void buildWorkshopsPanel()
   {
      //create a list
      workshops = new JList(seminars);
     
      //set selection mode
      workshops.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
     
      //set visible rows
      workshops.setVisibleRowCount(4);
     
      //create layout manager
      workshopsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
     
      //create a border
      workshopsPanel.setBorder(BorderFactory.createTitledBorder("Workshops"));
     
      //add list to panel
      workshopsPanel.add(workshops);
   }
  
   /**
      The getWorkshopCosts will get the total costs for the workshops
      the user will attend.
      @return workshopTotal will hold the total costs of the selected workshops
  */
  
    public double getWorkshopCosts()
   {
    
      double workshopTotal = 0.0; //total cost of selected workshops
     
      //Get the indices of selected values
      int[] selected = workshops.getSelectedIndices();
      for(int i = 0; i < selected.length; i++)
      {
          int sem_idx = selected[i];
          workshopTotal += seminar_cost[sem_idx];
      }
     
     
      return workshopTotal;
   }
  
   /**
      buildButtonPanel method will create and display the
      calculate, clear, and exit buttons.
   */
   private void buildButtonPanel()
   {
      // Create the buttons
      calcButton = new JButton("Calculate Charges");
      clearButton = new JButton("Clear");
      exitButton = new JButton("Exit");
     
     // Add an action listener to the buttons.
     calcButton.addActionListener(new CalcButtonListener());
     clearButton.addActionListener(new ClearButtonListener());
     exitButton.addActionListener(new ExitButtonListener());
    
     //add buttons to the panel
     buttonPanel.add(calcButton);
     buttonPanel.add(clearButton);
     buttonPanel.add(exitButton);
   }
  
   /**
      CalcButton Listener will handle calculating the
      charges for the event
   */
   private class CalcButtonListener implements ActionListener
   {
      /**
         actionPerformed method
         @param e An ActionEvent object.
      */
      public void actionPerformed(ActionEvent e)
      {
         double totalCost;
   
         //Display error message if user hasn't selected an attendant type or workshop
         if(getAttCost() == 0 || getWorkshopCosts() == 0)
            JOptionPane.showMessageDialog(null, String.format("Please make sure you've selected a registration type " +
                                                              "and at least one workshop to attend."));
        
         //calculate the total charge
         totalCost = getAttCost() + getDSCost() + getWorkshopCosts();

         JOptionPane.showMessageDialog(null, String.format("Total Charges: $%,.2f",totalCost));
      }
   }
  
   /**
      ClearButtonListener will handle clearing the info
      enter by the user when clicked
   */
   private class ClearButtonListener implements ActionListener
   {
      /**
         actionPerformed method
         @param e An ActionEvent object.
      */
      public void actionPerformed(ActionEvent e)
      {
         //write method to set attendant type to default setting
         attGroup.clearSelection();
        
         //write method to uncheck dinner and speech checkbox
         dinnerSpeech.setSelected(false);
        
         //write method to clear choices on workshop list
         workshops.clearSelection();
      }
   }
  
   /**
      ExitButtonListener is an action listener class for the
      exitButton component.
   */

   private class ExitButtonListener implements ActionListener
   {
      /**
         actionPerformed method
         @param e An ActionEvent object.
      */

      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);
      }
   }
  
   /**
      The main method creates an instance of the EventCharge class
      and causes it to display the GUI window.
   */
   public static void main(String[] args)
   {
      conferenceregistration person1 = new conferenceregistration();
   }
}

