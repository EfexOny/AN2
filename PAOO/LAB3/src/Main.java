public class Main {

    public static class Complex{
        int imag,real;

        Complex(){
            this.real=this.imag=0;
        }
        Complex(int real,int imag){
            this.real=real;
            this.imag=imag;
        }

        public  Complex adunare(Complex c1){
            this.real=this.real+c1.real;
            this.imag=this.imag+c1.imag;
            return this;
        };

        public Complex inmultire(Complex c1){
            this.real=(this.real*c1.real - this.imag*c1.imag);
            this.imag=(this.real*c1.imag + this.imag*c1.real);
            return this;
        }

        public Complex ridicareLaPutere(int n){
            if(n>0) {
                Complex rez = new Complex(1, 0);
                for (int i = 0; i < n; i++) {
                    rez.inmultire(this);
                }
                return rez;
            }else{
                return new Complex(0,0);
            }
        }

        public boolean equals(Complex c1){
            if((this.real==c1.real) && (this.imag==c1.imag)){
                return true;
            }else{
                return false;
            }
        };

        public String toString(){
            return "(" + this.real + "," + this.imag + ") ";
        }
    }

    public static class Stiva{
        int []vec;
        int index;

        Stiva(){
            vec = new int[100];
            index=-1;
        }
        Stiva(int dim){
            vec = new int[dim];
            index=-1;
        }

        void push(int data){
            if(index<vec.length){
                vec[++index]=data;
            }
        }

        void top(){
            System.out.println(vec[index]);
        }

        int pop(){
            if(index!=-1){
                return vec[index--];
            }
            return 0;
        }

        boolean isEmpty(){
            if(index==0) {
                return true;
            }else{
                return false;
            }
        }
    }

    public static class matrix{
        final int size;
        double [][] matr;

        public matrix(int size) {
            this.size = size;
            matr = new double[size][size];
        }

        matrix adunare(matrix m2){
            if(this.size==m2.size){

                for(int i=0;i<this.size;i++){
                    for(int j=0;j<this.size;j++){
                        this.matr[i][j]+=m2.matr[i][j];
                    }
                }
            }

            return this;
        }

        matrix dummy(){
            int cont=1;
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    this.matr[i][j]=cont++;
                }
            }
            return this;
        }

        matrix inmultire(matrix m2){
            matrix ret = new matrix(size);

            if(this.size==m2.size) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        for (int k = 0; k < size; k++) {
                            ret.matr[i][j] += this.matr[i][k] * m2.matr[k][j];
                        }
                    }
                }
            }
            return ret;
        }

        String toStringMatrix(){
            StringBuilder b=new StringBuilder();
            for(int i=0;i<this.size;i++){
                for(int j=0;j<this.size;j++){
                    b.append(this.matr[i][j]+ " ");
                }
                b.append("\n");
            }
            return b.toString();
        }
    }

    public static class Student implements Comparable<Student>{
        String name;
        int age;
        int grade;
        Student(){
            name = "";
            age=0;
            grade=0;
        }
        Student(String name,int age,int grade){
            this.name=name;
            this.age=age;
            this.grade=grade;
        }

        Student (Student s)
        {
            this.name = s.name;
            this.age = s.age;
            this.grade = s.grade;

        }


        String GetName(){
            return this.name;

        }


        int GetAge(){
            return this.age;
        }

        int GetGrade(){
            return this.grade;
        }

        String toStringStudent()
        {
            return "Nume:"+this.name+"\n"
                    +"Varsta:"+this.age+"\n"
                    +"Nota:"+this.grade+"\n";
        }


        Student cloneStudent(){
            return new Student(this);
        }

        public int compareTo(Student o){
            int nameCompare = this.name.compareTo(o.name);
            if (nameCompare != 0) {
                return nameCompare;
            }
            if (this.age != o.age) {
                return this.age - o.age;
            }
            return this.grade - o.grade;
//            if(this.grade > o.grade)
//                return 1;
//            if(this.grade < o.grade)
//                return -1;
        }


    }

    public static class StudentClass{
        Student []v;
        int n;
        final int N = 10;


        StudentClass(){
            this.v = new Student[N];
            this.n = 0;
        }
        StudentClass(int n){
            this.v = new Student[n];
            this.n = 0;
        }

        void Add(Student s){
            if(n<v.length){
                v[n++]=s;
            }
        }

        public void Print() {
            for (int i = 0; i < n; i++) {
                System.out.println(v[i].toStringStudent());
            }
        }

        public void Sort() {
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (v[j].compareTo(v[j + 1]) > 0) {
                        Student temp = v[j];
                        v[j] = v[j + 1];
                        v[j + 1] = temp;
                    }
                }
            }
        }

        public int Getn() {
            return n;
        }

    }

    public static void main(String[] args) {

        Complex nr1= new Complex(2,3);
        Complex nr2= new Complex(2,3);
        nr1.equals(nr2);
        nr1=nr1.ridicareLaPutere(2);
        System.out.println(nr1.toString());

        Stiva stack= new Stiva(3);
        stack.push(20);
        stack.push(22);
        stack.top();
        stack.pop();
        stack.top();

        matrix matr1,matr2;
        matr1=new matrix(3);
        matr2=new matrix(3);
        matr1.dummy();matr2.dummy();

        matr1.adunare(matr2);
        System.out.println(matr1.toStringMatrix());
        System.out.println(matr2.toStringMatrix());
        matr1=matr1.inmultire(matr2);
        System.out.println(matr1.toStringMatrix());

        StudentClass sc = new StudentClass(10);
        sc.Add(new Student("Marius", 19, 9));
        sc.Add(new Student("Prina", 20, 5));
        sc.Add(new Student("Oaina", 20, 6));
        sc.Add(new Student("Laina", 20, 7));
        sc.Add(new Student("Caina", 20, 8));
        sc.Add(new Student("Ceaina", 20, 9));
        sc.Add(new Student("Geaina", 20, 10));

        sc.Print();
        sc.Sort();
        System.out.println("AFTER");
        sc.Print();

        System.out.println(sc.v[2].compareTo(sc.v[0]));


    }
}