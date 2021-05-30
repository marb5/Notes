package com.example.notes.model.note;

/**
 *
 * @author marcin
 */
public class NoteWrite {
    private String name;
    private String content;
    private int boxId;

    public NoteWrite() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }
}
