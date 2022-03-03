/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocl1_p1_201612106;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author chana
 */
public class OCL1_P1_201612106 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        // TODO code application logic here
        UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        Principal principal = new Principal();
        principal.setVisible(true);
    }
    
}
