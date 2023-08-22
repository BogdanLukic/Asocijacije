package Models;

import Entities.*;

public class Association {
    private Column_A column_a;
    private Column_B column_b;
    private Column_C column_c;
    private Column_D column_d;
    private FinalAnswer finalAnswer;

    public Association(Column_A column_a, Column_B column_b, Column_C column_c, Column_D column_d, FinalAnswer finalAnswer) {
        this.column_a = column_a;
        this.column_b = column_b;
        this.column_c = column_c;
        this.column_d = column_d;
        this.finalAnswer = finalAnswer;
    }

    public Column_A getColumn_a() {
        return column_a;
    }

    public void setColumn_a(Column_A column_a) {
        this.column_a = column_a;
    }

    public Column_B getColumn_b() {
        return column_b;
    }

    public void setColumn_b(Column_B column_b) {
        this.column_b = column_b;
    }

    public Column_C getColumn_c() {
        return column_c;
    }

    public void setColumn_c(Column_C column_c) {
        this.column_c = column_c;
    }

    public Column_D getColumn_d() {
        return column_d;
    }

    public void setColumn_d(Column_D column_d) {
        this.column_d = column_d;
    }

    public FinalAnswer getFinalAnswer() {
        return finalAnswer;
    }

    public void setFinalAnswer(FinalAnswer finalAnswer) {
        this.finalAnswer = finalAnswer;
    }
}
