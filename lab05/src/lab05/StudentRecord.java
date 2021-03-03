package lab05;

public class StudentRecord {
    private String studentID;
    private float midterm;
    private float assignment;
    private float finalExam;
    private float finalMark;
    private char letterGrade;

    public StudentRecord(String studentID, float midterm, float assignment, float finalExam){
        this.studentID = studentID;
        this.midterm = midterm;
        this.assignment = assignment;
        this.finalExam = finalExam;
        setFinalMark(assignment,midterm,finalExam);
        setLetterGrade(finalMark);
    }

    public String getStudentID() {
        return studentID;
    }

    public float getMidterm() {
        return midterm;
    }

    public float getAssignment() {
        return assignment;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public float getFinalMark() {
        return finalMark;
    }

    public char getLetterGrade() {
        return letterGrade;
    }

    public void setFinalMark(float assignment,float midterm, float finalExam) {
        this.finalMark = assignment*0.20f + midterm*0.30f + finalExam*0.50f;
    }

    public void setLetterGrade(float finalMark) {
        if (finalMark>=80 && finalMark<=100){
            this.letterGrade = 'A';
        }
        else if (finalMark>=70 && finalMark<80) {
            this.letterGrade= 'B';
        }
        else if (finalMark>=60 && finalMark<70) {
            this.letterGrade=  'C';
        }
        else if (finalMark>=50 && finalMark<60) {
            this.letterGrade=  'D';
        }
        else if (finalMark<50){
            this.letterGrade= 'F';
        }
    }
}
