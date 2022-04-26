package edu.lewisu.cs.yasirtahir.a3pics2tries;

public class Pictures {
    private int pic;
    private String question;

    public Pictures(int pic, String question) {
        this.pic = pic;
        this.question = question;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
