//import javax.swing.*;
//import java.awt.event.*;
//import java.awt.*;
/**
 * Classe non utilizzata
 * @author Mattia Gaetano Greco
 * @version 1.0.0
 */
public class Menu
{
    /**
     * Esegue il metodo startGame che fa partire il gioco
     */
    public static void init()
    {
        //Gioco.finestra.getContentPane().setBackground(Color.BLACK);
        /*JLabel title = new JLabel(new ImageIcon("immagini/mainmenu.png"));
        title.setSize(512,512);
        title.setLocation(246, 50);
        JButton button = new JButton("Click!");
        button.setSize(200,50);
        button.setLocation(400, 750);
        //Gioco.finestra.add(button);
        Gioco.finestra.add(title);
        /*button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Gioco.finestra.getContentPane().removeAll();
                //Gioco.finestra.repaint();
                //Gioco.finestra.remove(title);
                Gioco.finestra.remove(button);
                button
                startGame();
            }
        });*/
        startGame();
    }
    /**
     * Fa partire il supermercato
     */
    private static void startGame()
    {
        Supermercato.init();
    }
}