package com.demo.model.operacion;


// Objeto creado para despliegue de maquinas en formato (necesita conectar con DB)
public class DisplayMachine {

    private String DisplayM;
    private int codigoMetodo;

    private int NumMaq;

    private String codigoRegla;

    public DisplayMachine() {

    }



    public DisplayMachine(Integer codigoMetodo, int NumMaq) {
        this.codigoMetodo = codigoMetodo;
        this.NumMaq = NumMaq;
        this.DisplayM = "Sin Especificar";
        this.codigoRegla = "NOT";
        procesaDisplay();
    }

    public void procesaDisplay(){
        switch (codigoMetodo) {
            case 1:
                this.DisplayM = "ADTI-01, Kit de adhesión de tintas";
                break;
            case 2:
                switch (codigoRegla) {
                    case "REGL-06":
                        DisplayM = "REGL-06, Regla de 30 cm";
                        break;
                    case "REGL-07":
                        DisplayM = "REGL-07, Regla de 30 cm";
                        break;
                    case "REGL-08":
                        DisplayM = "REGL-08, Regla de 60 cm ARLY";
                        break;
                    case "REGL-09":
                        DisplayM = "REGL-09, Regla de 60 cm ARLY";
                        break;
                    case "REGL-14":
                        DisplayM = "REGL-14, Regla de 150 cm Keuffl&Esser profesional";
                        break;
                }
                break;
            case 3:
                this.DisplayM = "MICR-02, ID-C112EXBS Micrómetro Mitutoyo";
                break;
            case 4:
                this.DisplayM = "BALA-02, EP 214 C Balanza analítica OHAUS";
                break;
            case 5:
                this.DisplayM = "BALA-02, EP 214 C Balanza analítica OHAUS; HORN-05 UFE 600 Horno de convección Memmert";
                break;
            case 6:
                this.DisplayM = "MICR-02, ID-C112EXBS Micrómetro Mitutoyo; MICRO-04, BA410 Microscopio óptico Motic; PACA-05";
                break;
            case 7:
                this.DisplayM = "BALA-02, EP 214 C Balanza analítica OHAUS";
                break;
            case 8:
                switch (codigoRegla) {
                    case "FTIR-14":
                        DisplayM = "FTIR-14, Nicolet iS20/iZ10 Espectrofotómetro infrarrojo FTIR Thermo Scientific";
                        break;
                    case "FTIR-20":
                        DisplayM = "FTIR-20, Frontier Espectrofotómetro infrarrojo FTIR/NIR PerkinElmer";
                        break;
                    case "PACA-05":
                        DisplayM = "PACA-05, HP88857100 Parrilla de calentamiento Thermo Scientific";
                        break;
                }
                break;
        }
    }

    public Integer getCodigoMetodo() {
        return codigoMetodo;
    }

    public void setCodigoMetodo(Integer codigoMetodo) {
        this.codigoMetodo = codigoMetodo;
    }
    public String getDisplayM() {
        return DisplayM;
    }

    public void setDisplayM(String DisplayM) {
        this.DisplayM = DisplayM;
    }

    public Integer getNumMaq() {
        return NumMaq;
    }

    public void setNumMaq(Integer NumMaq) {
        this.NumMaq = NumMaq;
    }

    public String getcodigoRegla() {
        return codigoRegla;
    }

    public void setcodigoRegla(String codigoRegla) {
        this.codigoRegla = codigoRegla;
        procesaDisplay();
    }

    public String getTruncate(double NumDisp)
    {
        Integer i;
        String Num;
        NumDisp=NumDisp*1000;
        System.out.println(NumDisp);
        i=(int) Math.abs(NumDisp);
        System.out.println(i);
        Num=Integer.toString(i);
        if (Num.length()==1)
            Num="0.00"+Num;
        else
            if (Num.length()==2)
                Num="0.0"+Num;
            else
                if (Num.length()==3)
                    Num="0."+Num;
                else{
                    Num=Num.substring(0,Num.length()-3)+"."+Num.substring(Num.length()-3,Num.length());
                }

        System.out.println(Num);
        return Num;
    }

}
