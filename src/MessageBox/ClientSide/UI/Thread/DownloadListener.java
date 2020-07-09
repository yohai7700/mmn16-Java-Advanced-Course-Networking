package MessageBox.ClientSide.UI.Thread;

import MessageBox.Message;

import java.util.List;

public interface DownloadListener{
    void onDownload(List<Message> messages);
}
