package edu.lewisu.cs.yasirtahir.a3pics2tries;

public class Question {
    String imageId1;
    String imageId2;
    String imageId3;
    String hint;
    String answer;


    public Question(String imageId1, String imageId2, String imageId3, String hint, String answer) {
        this.imageId1 = imageId1;
        this.imageId2 = imageId2;
        this.imageId3 = imageId3;
        this.hint = hint;
        this.answer = answer;
    }

    public String getImageId1() {
        return imageId1;
    }

    public void setImageId1(String imageId1) {
        this.imageId1 = imageId1;
    }

    public String getImageId2() {
        return imageId2;
    }

    public void setImageId2(String imageId2) {
        this.imageId2 = imageId2;
    }

    public String getImageId3() {
        return imageId3;
    }

    public void setImageId3(String imageId3) {
        this.imageId3 = imageId3;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "imageId1='" + imageId1 + '\'' +
                ", imageId2='" + imageId2 + '\'' +
                ", imageId3='" + imageId3 + '\'' +
                ", hint='" + hint + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
