package br.com.desafio.modelo;

import br.com.desafio.dto.DtoMoedaConsulta;
import com.google.gson.annotations.SerializedName;

public class Moedas
{
    @SerializedName("ARS")
    private Double ars;
    @SerializedName("BRL")
    private Double brl ;
    @SerializedName("BOB")
    private  Double bob;
    @SerializedName("CAD")
    private  Double cad;
    @SerializedName("CLP")
    private Double clp;
    @SerializedName("EUR")
    private Double eur;
    @SerializedName("JPY")
    private Double jpy;
    @SerializedName("MXN")
    private Double mxn;
    @SerializedName("RUB")
    private Double rub;
    @SerializedName("USD")
    private Double usd;

    public Moedas() {
    }






    public Double converteMoeda(Double quantia, String codMoeda){
        {  Double valorConvertido = 0.0;

           switch (codMoeda){
               case "ARS": {
                  valorConvertido = ars * quantia;

                           break;
               }
               case "BRL": {
                   valorConvertido = brl * quantia;
                   break;
               }
               case "BOB": {
                   valorConvertido = bob * quantia;
                   break;
               }
               case "CAD": {
                   valorConvertido = cad * quantia;
                   break;
               }
               case "CLP": {
                   valorConvertido = clp * quantia;
                   break;
               }
               case "EUR": {
                   valorConvertido = eur * quantia;
                   break;
               }
               case "JPY": {
                   valorConvertido = jpy * quantia;
                   break;
               }
               case "MXN": {
                   valorConvertido = mxn * quantia;
                   break;
               }
               case "RUB": {
                   valorConvertido = rub * quantia;
                   break;
               }
               case "USD": {
                   valorConvertido = usd * quantia;
                   break;
               }

           }



    return valorConvertido;
    }
}
    public void converteMoedaEmLote(Double quantia, Moedas codMoeda){
        {
            codMoeda.ars *= quantia;
            codMoeda.brl *= quantia;
            codMoeda.bob *= quantia;
            codMoeda.cad *= quantia;
            codMoeda.clp *= quantia;
            codMoeda.eur *= quantia;
            codMoeda.jpy *= quantia;
            codMoeda.mxn *= quantia;
            codMoeda.rub *= quantia;
            codMoeda.usd *= quantia;


        }

        System.out.println(codMoeda);


    }






    @Override
    public String toString() {
        return "Moedas:" +
                "\n ars Peso argentino= ₱ " + String.format("%.2f",ars) +
                ",\n brl Real Brailileiro= R$ " + String.format("%.2f", brl) +
                ",\n bob Bolivar Boliviano  = Bs.F " + String.format("%.2f", bob) +
                ",\n cad Dolar Canadense= C$ " + String.format("%.2f", cad) +
                ",\n clp Peso Chileno = CLP$ " + String.format("%.2f", clp) +
                ",\n eur Euro= € " + String.format("%.2f", eur) +
                ",\n jpy Iene Japonês= ¥ "+ String.format("%.2f", jpy) +
                ",\n mxn Peso Mexicano= ₱ " + String.format("%.2f", mxn) +
                ",\n rub Rublo Russo= ₽ " + String.format("%.2f", rub) +
                ",\n usd Dolar americano= $" + String.format("%.2f", usd) +
                "\n Moedas com o valor 0,00 são de cotação inferior a 0,01.";


    }



}
