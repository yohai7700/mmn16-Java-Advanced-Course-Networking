package MessageBox.ClientSide.UI.MessageDraft;

import MessageBox.ClientSide.UnsubscribedUserException;

public interface UnsubscribedUserHandler {
    void handle(UnsubscribedUserException e);
}
