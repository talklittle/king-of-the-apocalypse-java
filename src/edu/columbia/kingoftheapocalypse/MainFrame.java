package edu.columbia.kingoftheapocalypse;

/*
 * GridLayoutDemo.java
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame {
    JComboBox horGapComboBox;
    JComboBox verGapComboBox;
//    JButton applyButton = new JButton("Apply gaps");
    GameMap gameMap;
    
    public MainFrame(String name, File mapFile) {
        super(name);
        setResizable(false);
        
        gameMap = new GameMap(mapFile);
    }
    
    public void addComponentsToPane(final Container pane) {
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(gameMap.getLayout());
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2, 3));
        
        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        compsToExperiment.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5),
                (int)(buttonSize.getHeight() * 3.5)));
        
        //Add buttons to experiment with Grid Layout
//        compsToExperiment.add(new JButton("Button 1"));
//        compsToExperiment.add(new JButton("Button 2"));
//        compsToExperiment.add(new JButton("Button 3"));
//        compsToExperiment.add(new JButton("Long-Named Button 4"));
//        compsToExperiment.add(new JButton("5"));
        gameMap.populatePanel(compsToExperiment);
        
        //Add controls to set up horizontal and vertical gaps
        controls.add(new Label("Horizontal gap:"));
        controls.add(new Label("Vertical gap:"));
        controls.add(new Label(" "));
//        controls.add(horGapComboBox);
//        controls.add(verGapComboBox);
//        controls.add(applyButton);
        
//        //Process the Apply gaps button press
//        applyButton.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                //Get the horizontal gap value
//                String horGap = (String)horGapComboBox.getSelectedItem();
//                //Get the vertical gap value
//                String verGap = (String)verGapComboBox.getSelectedItem();
//                //Set up the horizontal gap value
//                experimentLayout.setHgap(Integer.parseInt(horGap));
//                //Set up the vertical gap value
//                experimentLayout.setVgap(Integer.parseInt(verGap));
//                //Set up the layout of the buttons
//                experimentLayout.layoutContainer(compsToExperiment);
//            }
//        });
        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI(String mapFileName) {
    	// FIXME: initializing images belongs in a different method
    	// initialize Unit.defaultImages
    	Unit.defaultImages = new Image[2];
    	Unit.defaultImages[0] = new ImageIcon(MainFrame.class.getResource("/res/images/blue_unit.png")).getImage();
    	Unit.defaultImages[1] = new ImageIcon(MainFrame.class.getResource("/res/images/red_unit.png")).getImage();
    	
        //Create and set up the window.
        MainFrame frame = new MainFrame("King of the Apocalypse", new File(mapFileName));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
        // Begin the main game loop.
        frame.beginLoop();
    }
    
    // http://java.sun.com/docs/books/tutorial/uiswing/examples/components/FrameDemo2Project/src/components/FrameDemo2.java
    //Creates an icon-worthy Image from scratch.
    protected static Image createFDImage() {
        //Create a 16x16 pixel image.
        BufferedImage bi = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);

        //Draw into it.
        Graphics g = bi.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 15, 15);
        g.setColor(Color.RED);
        g.fillOval(5, 3, 6, 6);

        //Clean up.
        g.dispose();

        //Return it.
        return bi;
    }
    
    private void beginLoop() {
    	gameMap.setListeners(Constants.GAME_STATE_PLAY);
    }

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        final String mapFileName = args[0];
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(mapFileName);
            }
        });
    }
}
