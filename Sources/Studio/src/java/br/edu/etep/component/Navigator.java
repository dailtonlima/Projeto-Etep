/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.etep.component;


import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.toolbar.Toolbar;

/**
 *
 * @author Dailton
 */
public class Navigator {
    
    private CommandButton btSave = null;
    private CommandButton btDelete = null;
    private CommandButton btNew = null;
    private CommandButton btCancel = null;
        
    private Toolbar toolbar = null;
    
    private List<CommandButton> buttons = new ArrayList<CommandButton>();

    public Navigator(UIComponent parent) {
        newInstance(parent);
    }
    
    
    private void newInstance(UIComponent parentComponent){
        
        toolbar = new Toolbar();
        toolbar.setParent(parentComponent);
        //Botão salvar
        btSave = new CommandButton();
        btSave.setLabel("Salvar");
        btSave.setParent(toolbar);
        buttons.add(btSave);
        // Botão Apagar
        btDelete = new CommandButton();
        btDelete.setLabel("Apagar");
        btDelete.setParent(toolbar);
        buttons.add(btDelete);
        //Botão Cancelar
        btCancel = new CommandButton();
        btCancel.setLabel("Cancelar");
        btCancel.setParent(toolbar);
        buttons.add(btCancel);
        //Botão Novo
        btNew = new CommandButton();
        btNew.setLabel("Novo");
        btNew.setParent(toolbar);
        buttons.add(btNew);
        
    }
       
    //GETTERS & SETTERS    
    public CommandButton getBtSave() {
        return btSave;
    }

    public void setBtSave(CommandButton btSave) {
        this.btSave = btSave;
    }

    public CommandButton getBtDelete() {
        return btDelete;
    }

    public void setBtDelete(CommandButton btDelete) {
        this.btDelete = btDelete;
    }

    public CommandButton getBtNew() {
        return btNew;
    }

    public void setBtNew(CommandButton btNew) {
        this.btNew = btNew;
    }

    public CommandButton getBtCancel() {
        return btCancel;
    }

    public void setBtCancel(CommandButton btCancel) {
        this.btCancel = btCancel;
    }
    
    
}
