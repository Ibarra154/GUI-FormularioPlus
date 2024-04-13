/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scripts;

import Panel.Login;
import Panel.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author ivan_
 */
public class run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login login = new Login();
        login.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                Panel ventana = new Panel();
                ventana.editable();
            }
        });
        
    }
    
}
