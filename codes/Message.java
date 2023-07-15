import java.io.File;
import java.time.LocalDateTime;

public class Message {
    private String id;
    private int likeNum;
    private int dislikeNum;
    private boolean isPinned;
    private LocalDateTime date;

    public Message(String id, int likeNum, int dislikeNum, boolean isPinned, LocalDateTime date,
                   String caption, File file) {
        this.id = id;
        this.likeNum = likeNum;
        this.dislikeNum = dislikeNum;
        this.isPinned = isPinned;
        this.date = date;
    }
    public Message(String id, int likeNum, int dislikeNum, boolean isPinned, LocalDateTime date,
                   String caption) {
        this.id = id;
        this.likeNum = likeNum;
        this.dislikeNum = dislikeNum;
        this.isPinned = isPinned;
        this.date = date;
    }



}
