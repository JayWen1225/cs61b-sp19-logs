public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /* First Constructor */
    public Body(double xP, double yP, double xV,
                double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    
    /* Second Constructor */    
    public Body (Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b1){
        double xxDistance;
        double yyDistance;
        double Distance;

        xxDistance = b1.xxPos - this.xxPos;
        yyDistance = b1.yyPos - this.yyPos;
        Distance = Math.sqrt(Math.pow(xxDistance, 2)  + Math.pow(yyDistance, 2));
        return Distance;
    }
    public double calcForceExertedBy(Body b){
        double r;
        double force;
        double G = 6.67e-11;

        r = this.calcDistance(b);
        force = (G * this.mass * b.mass / (r * r));
        return force;
    }
    public double calcForceExertedByX(Body b) {
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        double dx = b.xxPos - this.xxPos;
        double Fx = F * dx / r;
        return Fx;
    }

    public double calcForceExertedByY(Body b) {
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        double dy = b.yyPos - this.yyPos;
        double Fy = F * dy / r;
        return Fy;
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double Fx = 0;
        for (Body b : bodies) {
            if (b.equals(this)) {
                continue;
            }
            Fx += calcForceExertedByX(b);
        }
        return Fx;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double Fy = 0;
        for (Body b : bodies) {
            if (b.equals(this)) {
                continue;
            }
            Fy + = calcForceExertedByY(b);
        }
        return Fy;
    }

    public void update(double dt,double fx,double fy){
        double aNetx;
        double aNety;
        double vNewx;
        double vNewy;
        double xxNewPos;
        double yyNewPos;

        aNetx = fx / this.mass;
        aNety = fy / this.mass;

        vNewx = this.xxVel + dt * aNetx;
        vNewy = this.yyVel + dt * aNety;

        xxNewPos = this.xxPos + dt * vNewx;
        yyNewPos = this.yyPos + dt * vNewy;

        this.xxPos = xxNewPos;
        this.yyPos = yyNewPos;
        this.xxVel = vNewx;
        this.yyVel = vNewy;


     }
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos,"images/" + this.imgFileName);
    }
}
