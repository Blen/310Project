import java.awt.*;
import java.util.Timer;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;  
import java.util.ArrayList; 
import java.util.Random;
import java.util.TimerTask;
import java.applet.Applet;

import javax.swing.*;
import javax.swing.border.Border;

public class Project310 extends JApplet implements ActionListener {
	
	public JTextField resultLabel;
	protected JButton sendB;
	protected JTextField textField;
    protected JTextArea textArea;
    protected ImageIcon icon;
    private final static String newline = "\n";
    protected JLabel emo;
    protected int life;
    Panel sPanel, cPanel;
    protected int check, trigger, trigger2,timertrigger, idle;
    
    
    protected String [] lstates = {"what's an impression of another game?","Ask me about another game.","any other game you think is cool?","ask me my opinion on another classic."};
    protected String [] leads = {"you kiddin me bro? ", "dude...","are you for real? ","Oh BOY... ","Really!? ","Are you serious? "};
    protected String [] anger = {"...not cool bro","you've got to be kidding me...","you couldn't be more wrong","you fool...","I hate to say it...but you don't know games","you're making me angry doc.","you really got some nerve, you know that?"}; // negative comments
    protected String [] compliments = {"You're a pretty cool cat...you know that?", "You have such great tastes!", "You know, you're a pretty smart person!", "That's interesting! I feel the same.","You're so darn cool!","You read my mind..."};  //complitments
    protected String [] goodgames = {"mario","mega man","battletoads","blades of steel","zelda","contra","castlevania","duck hunt","tetris","dr. mario","excitebike","metroid","ice hockey","duck tales","metal gear","bubble bobble","tmnt","teenage mutant ninja turtles","double dragon","battle toads","ski or die","batman","abadox","balloon fight","bionic commando","adventure island","blaster master","bomber man","dig dug","donkey kong","earth bound","gi joe","ikari warriors"};
    protected String [] goodadjectives = {"love","my favorite","good","awesome","amazing","great","astonishing","impressive","magnificent"," mind-blowing","stunning","wonderful","insanely good","wonderful","enjoy","cool","like","classic","mind blowing","incredible","wicked"};
    protected String [] badadjectives = {"brutal","bad","terrible","appalling","atrocious","awful","dreadful","petrifying","poor","worse than the twilight series","unpleasant","repulsive","worse than the hunger games movie","sucks","the worst","hate","ok","trash","garbage","horrible","stupid"};
    protected String [] badgames = {"x-men","volleyball","street cop","bible adventures","garfield","where's waldo","gilligan's island","final fantasy","golf","jaws","skate or die","sk8 or die","silver surfer","barbie","airwolf","ballon fight","back to the future","battle chess","beetle juice","blue shadow","caveman games","cobra command","darkman","dragonball z","tennis","ghost busters"};
    protected String [] badwords = {"is","a","you","i","I","think","is"};
    protected String[] quest = {"what", "do", "where", "how", "why","what's","could","whats","tell","can"};
    
    public Project310(){
    	
    	Border border = BorderFactory.createLineBorder(Color.BLACK);
    	
    	life = 9;
    	check = 0;
    	
    	
    	
    	sPanel = new Panel();
    	cPanel = new Panel();
    	
    	sPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	cPanel.setLayout(new BorderLayout());
    	
    	cPanel.setBackground(Color.white);
    	sPanel.setBackground(Color.white);
    
    	sendB = new JButton("Send");
    	sendB.addActionListener(this);
    	
    	textArea = new JTextArea(5, 20);  // response field
        textArea.setEditable(false);
        textArea.setBorder(border);
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setForeground(Color.blue);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy( JScrollPane. VERTICAL_SCROLLBAR_ALWAYS );
        
        
        textField = new JTextField(20);
        textField.addActionListener(this);   // input field
        textArea.setText("Jerry: Go ahead! Ask me my opinon on a nintendo game."+ newline);
        
        ImageIcon icon = new ImageIcon ("smile.gif");
        emo = new JLabel("", icon, SwingConstants.CENTER);		//A change to smile.
     
        Timer timer1 = new Timer();							// timer
        long delay1 = 12*1000;
        
      
        timer1.schedule(new Aitask(), delay1);

        cPanel.add(emo, BorderLayout.EAST);
     //   cPanel.add(textArea, BorderLayout.CENTER);
        cPanel.add(scrollPane, BorderLayout.CENTER);
        sPanel.add(textField);
        sPanel.add(sendB);
        
        this.setLayout(new BorderLayout());
        add(sPanel, BorderLayout.SOUTH);
        add(cPanel, BorderLayout.CENTER);
                
    }
    
    class Aitask extends TimerTask {
        public void run() {
        	Random generator = new Random();				//AI response after time ends.
        	int num = generator.nextInt(20);
        	if(timertrigger == 0)
        	{
        	textArea.append("Jerry: Hey? What do you think of "+goodgames[num]+ "?"+ newline);
        	trigger = 1; // gets ready for 'its' response
        	idle = 0;  //sets idle back to 0
        	}
        }
    }
 
    public void actionPerformed(ActionEvent event) {
    	String[] words = null;
    	int badcheck = badwords.length;
    	Random generator = new Random();
    	Random generator2 = new Random();
    	
        Timer timer2 = new Timer();							// timer for idle user 
        long delay2 = 12*1000;
    	
    	
    	
    															// maybe answer 'who' questions later?
    	String topic;
        String user = textField.getText();  					// takes user input
        user = user.toLowerCase();								// converts input to lower case
        
     						
        textArea.append("-" + user + newline);   	
        user = user.replace('?',' ');							// removes question mark from string
        
        String test = "HI !";
       check = 0; 
        
        words = user.split("\\s+");
        List<String> arrList = new ArrayList<String>(Arrays.asList(user.split(" ")));

        for (int i = 0; i < words.length; i++) 
        {
            words[i] = words[i].replaceAll("[\t]", ""); //split String into an array
        }
        
        
        
        final int N = words.length;				// get length of array to search
        
        if(words[0].equals(quest[0]) || words[0].equals(quest[1]) || words[0].equals(quest[3]) || words[0].equals(quest[4]) || words[0].equals(quest[5])|| words[0].equals(quest[6]) || words[0].equals(quest[7]) || words[0].equals(quest[8])|| words[0].equals(quest[9]))
       {

        	
        int pos = searchme(words[0], words);			// Move index words[0] - the question word
        for (int t = pos; t < N-1; t++) 
        {
        words[t] = words[t + 1];
        }
        
   //     List<String> wordList = Arrays.asList(words);  // Move array into arraylist*
       
       
        
        arrList.remove(0);								// removes question word
      
        for (int i = 0; i < badwords.length; i++) 
        {
            if (arrList.contains(badwords[i]))
            		{
            			arrList.remove(badwords[i]);		//if list contains a bad word - remove
            		}
        }
        
        
        if (!arrList.isEmpty()) {
        	
        	String curr = arrList.get(arrList.size()-1);       	
        	}

        
         String listString = "";						// converts arraylist to string
        
        for (String s : arrList)
        {
            listString += s + " ";
        }
        
        if(listString.contains("your favorite"))
        {
        	textArea.append("Jerry: my favorite game is Contra!" + newline);
        	trigger2 = 1;
        	timertrigger = 1;
        }
        
        for (int i = 0; i < goodgames.length; i++) 
        {
            if (listString.contains(goodgames[i]))
            		{
            				int num = generator.nextInt(11);
            				int num1 = generator.nextInt(6);
            				textArea.append("Jerry: "+ leads[num1] + goodgames[i] + " is " + goodadjectives[num] + newline);
            				textArea.append("Jerry: What do you think of " + goodgames[i] + " ?"  + newline);           		
            				check++;
            				trigger = 1; 					// 1 - triggers good
            				timertrigger = 1;
            		}
            
           
            
        }
        
        for (int x = 0; x < badgames.length; x++) 
        {
        if(listString.contains(badgames[x]))
		{
        	int num1 = generator.nextInt(6);
        	int num = generator.nextInt(11);
				textArea.append("Jerry: "+ leads[num1] + badgames[x] + " is " + badadjectives[num] + newline);
				textArea.append("Jerry: What do you think of " + badgames[x] + " ?"  + newline);
				check++;
				timertrigger = 1;
				trigger = 2;							// triggers bad.
		} 		       
        }
        
     
       
        if(check == 0 && trigger2 != 1)
            	{
            		textArea.append("never heard of it..." + newline);		//response only if doesn't ask favorite game
            		check = 0;
            		timertrigger = 0;
            		idle = idle + 1;
            	}   
        trigger2 = 0;
        }
        else
        {
        	String user2 = textField.getText();  					// takes user input
		     user2 = user.toLowerCase();	
		     
		     List<String> arrListgood = new ArrayList<String>(Arrays.asList(user2.split(" ")));
		     
		     for (int k = 0; k < badwords.length; k++) 
		        {
		            if (arrListgood.contains(badwords[k]))
		            		{
		            			arrListgood.remove(badwords[k]);		//if list contains a bad word - remove
		            		}
		        }
		     
		     
		     String listStringgood = "";						// converts arraylistgood to string
		        
		        for (String s : arrListgood)
		        {
		            listStringgood += s + " ";
		        }
   
			
			
 for (int i = 0; i < goodgames.length; i++) 
  {	
	 if (listStringgood.contains(goodgames[i]))
	 {
			for (int n = 0; n < goodadjectives.length; n++) 
	        { if (listStringgood.contains(goodadjectives[n]))
	        	{
	        	int num = generator.nextInt(6);
	        	int num2 = generator2.nextInt(4);
	        	textArea.append("Jerry: " + compliments[num]  + newline);
	        	life = life + 1;									// improves AI's mood
	        	
	        	textArea.append("Jerry: " + lstates[num2]  + newline); //continues convo
	        	
	        	check++;
	        	timertrigger = 0;
	        	idle = 1;
	        	}
	        	
	        }
			
			for (int n = 0; n < badadjectives.length; n++) 
	        { if (listStringgood.contains(badadjectives[n]))
	        	{
	        	int num = generator.nextInt(7);
	        	int num2 = generator2.nextInt(4);
	        	textArea.append("Jerry: " + anger[num]+ newline);			// makes AI angry
	        	life = life-1;
	        	
	        	textArea.append("Jerry: " + lstates[num2]  + newline);
	        	check++;
	        	timertrigger = 0;										//checking if no response given, resets timer.
	        	idle = 1;
	        	}
	        	
	        }
	  }
   }
 
 for (int i = 0; i < badgames.length; i++) 							// bad games compliment
 {	
	 if (listStringgood.contains(badgames[i]))
	 {
			for (int n = 0; n < goodadjectives.length; n++) 
	        { if (listStringgood.contains(goodadjectives[n]))
	        	{
	        	int num = generator.nextInt(7);
	        	int num2 = generator2.nextInt(4);
	        	textArea.append("Jerry: " + anger[num]  + newline);
	        	life = life-1;										// complimenting a bad game - angers AI
	        	
	        	textArea.append("Jerry: " + lstates[num2]  + newline);
	        	check++;	
	        	timertrigger = 1;
	        	idle = 1;
	        	}
	        	
	        }
			
			for (int n = 0; n < badadjectives.length; n++) 
	        { if (listStringgood.contains(badadjectives[n]))
	        	{
	        	int num = generator.nextInt(6);
	        	int num2 = generator2.nextInt(4);
	        	textArea.append("Jerry: " + compliments[num]+ newline);	// talking about a good game as bad
	        	life = life + 1;
	        	
	        	textArea.append("Jerry: " + lstates[num2]  + newline);
	        	check++;	
	        	timertrigger = 1;
	        	idle = 1;
	        	}
	        	
	        }
	  }
	
  }
 


 if(trigger == 2 && words[0].equals("its") || words[0].equals("it's") || words[0].equals("it"))  // feature that responds to 'its' after talking about a game
{
	 for (int n = 0; n < goodadjectives.length; n++) 
	{
	 if (listStringgood.contains(goodadjectives[n]))
		 
	 {
		 int num = generator.nextInt(11);
		 textArea.append("Jerry:  no it's not...it's " + badadjectives[num] + newline);
		trigger = 0;
		check++;
		idle = 1;
		timertrigger = 0;
	 }
	 
	 if (listStringgood.contains(badadjectives[n]))
		 
	 {
		 int num = generator.nextInt(11);
		 textArea.append("Jerry:  I disagree! It's " + goodadjectives[num] + newline);
		trigger = 0;
		check++;
		idle = 1;
		timertrigger = 0;
	 }
	 
	}
}



 if(trigger == 1 && words[0].equals("its") || words[0].equals("it's") || words[0].equals("it"))
{
	 for (int n = 0; n < goodadjectives.length; n++) 
	{
	 if (listStringgood.contains(goodadjectives[n]))
		 
	 {
		 int num = generator.nextInt(11);
		 int num1 = generator.nextInt(6);
		 
		 textArea.append("Jerry:  "+ leads[num1] + "it's " + goodadjectives[num] + newline);
		trigger = 0;
		check++;
		idle = idle + 1;
		timertrigger = 0;
	 }
	 
	 if (listStringgood.contains(badadjectives[n]))
		 
	 {
		 int num = generator.nextInt(11);
		 textArea.append("Jerry:  You're so wrong! it's " + goodadjectives[num] + newline);
		trigger = 0;
		check++;
		idle = idle + 1;
		timertrigger = 0;
	 }
	 
	}
}
 
 if(idle == 1)
 {
	 timer2.schedule(new Aitask(), delay2);
 }
 
  if(check == 0)
	 {
		 textArea.append("Jerry: I couldn't understand you. Let's talk about Nintendo games!" + newline);
 		check = 0;
 		idle = idle + 1;
	 }
 
 
        }
        	
     //   textArea.append(test + newline + life);
        textField.selectAll();
        textArea.setCaretPosition(textArea.getDocument().getLength());  
        textField.setText(null);

        if(life == 10)
        {
        emo.setIcon(new ImageIcon("shout.gif"));			//emotion definitions
        }
        if(life == 8)
        {emo.setIcon(new ImageIcon("question.gif"));			//emotion definitions
        }
        
        if(life == 6)
        {emo.setIcon(new ImageIcon("anger.gif"));
        }
        
        if(life == 4)
        {emo.setIcon(new ImageIcon("fire.gif"));
        }
        
     
}
    
    
    
        private int searchme(String key, String[] words) //Search method checking if array contains string
    {
    	for (int i = 0; i < words.length; i++)
            if (words[i] == key) {
                return i;
            }
        return -1;
	}

	private static void GUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("NES Chat 2012");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        
        //Add contents to the window.
        frame.add(new Project310());
        frame.setSize(750, 750);
        //Display the window.
       // frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               GUI();
               
               
               
            }
        });
    }
    }



