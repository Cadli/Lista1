package pl.edu.uwr.pum.lista1;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    private String mTextResId;
    private String[] mAnswers;
    private int mCorrectAnswerIndex;
    private boolean mAnswered;

    public Question(String TextResId, String[] Answers, int CorrectAnswerIndex, boolean Answered) {
        mTextResId = TextResId;
        mAnswers = Answers;
        mCorrectAnswerIndex = CorrectAnswerIndex;
        mAnswered = Answered;
    }

    protected Question(Parcel in) {
        mTextResId = in.readString();
        mAnswers = in.createStringArray();
        mCorrectAnswerIndex = in.readInt();
        mAnswered = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTextResId);
        dest.writeStringArray(mAnswers);
        dest.writeInt(mCorrectAnswerIndex);
        dest.writeByte((byte) (mAnswered ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getTextResId() {
        return mTextResId;
    }

    public void setTextResId(String textResId) {
        this.mTextResId = textResId;
    }

    public String[] getAnswers() {
        return mAnswers;
    }

    public void setAnswers(String[] answers) {
        this.mAnswers = answers;
    }

    public int getCorrectAnswerIndex() {
        return mCorrectAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.mCorrectAnswerIndex = correctAnswerIndex;
    }

    public boolean isAnswered() {
        return mAnswered;
    }

    public void setAnswered(boolean answered) {
        this.mAnswered = answered;
    }
}
