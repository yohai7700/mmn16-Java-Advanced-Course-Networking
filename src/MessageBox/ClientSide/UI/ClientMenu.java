package MessageBox.ClientSide.UI;

import MessageBox.ClientSide.Client;
import MessageBox.ClientSide.UI.ClientStructure.ClientPanel;
import MessageBox.ClientSide.UI.MessageDraft.MessageDraftPanel;
import MessageBox.ClientSide.UI.UserMessages.UserMessagesPanel;

import javax.swing.*;

/**
 * Split pane of 2 client panels, a show messages panel and a message draft panel.
 */
public class ClientMenu extends JSplitPane {
    private final ClientPanel messageDraftPanel, userMessagesPanel;

    public ClientMenu(Client client){
        super(JSplitPane.HORIZONTAL_SPLIT);
        messageDraftPanel = new MessageDraftPanel(client);
        userMessagesPanel = new UserMessagesPanel(client, messageDraftPanel::reply);
        addComponents();
    }

    private void addComponents(){
        add(userMessagesPanel);
        add(messageDraftPanel);
    }

    public void setClient(Client client){
        messageDraftPanel.setClient(client);
        userMessagesPanel.setClient(client);
        setEnabled(client != null);//enables panels only if client connected
    }

    //enabling with sub-components
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        userMessagesPanel.setEnabled(enabled);
        messageDraftPanel.setEnabled(enabled);
    }
}
