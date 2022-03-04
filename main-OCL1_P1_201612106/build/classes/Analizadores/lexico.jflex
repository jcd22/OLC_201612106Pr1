/* ////////////////////////  CODIGO DE USUARIO  ////////////////////////////// */

// ------------- PAQUETES E IMPORTACIONES
package Analizadores;
import java_cup.runtime.Symbol;
import java.util.ArrayList;

%%
/* ////////////////////// OPCIONES Y DECLARACIONES //////////////////////// */

%class lexico_j //nombre .java
%public
%line
%char
%cup
%unicode
%ignorecase

//CODIGO JAVA

%init{  
    yyline = 1;
    yychar = 1;
   

%init}

%{
     ArrayList<M_Error> errores_l = new ArrayList<M_Error>();
%}


//EXPRESIONES REGULARES
//[ \t\r\n\f]
BLANCOS=[ \r\t]+       
D=[0-9]+                
DD=[0-9]+("."[  |0-9]+)?       
CADENA = [\"]([^\"\n]|(\\\")|(\\\')|(\\n))*[\"]      //agregado: \' , \n 
CADENA2 = [\']([^\"\n]|(\\\")|(\\\')|(\\n))*[\']     //agregado: \' , \n
ID=[A-Za-z]+["_"0-9A-Za-z]*
COMENT_M = "<!" [^"!>"]* "!>"
COMENT_L = "//" [^\n]* [\n]
CUALQUIERA = [\x20-\x7D] //<--

%%

/* //////////////////////// REGLAS LEXICAS ////////////////////////////// */

//LENGUAJE Proyecto

//palabras reservadas
"CONJ" {return new Symbol(sym.conj,yyline,yychar,yytext());}

//varios simbolos
"{"  {return new Symbol(sym.llavea,yyline,yychar,yytext());}
"}"  {return new Symbol(sym.llavec,yyline,yychar,yytext());}
":"  {return new Symbol(sym.dospuntos,yyline,yychar,yytext());}
"->" {return new Symbol(sym.flechad,yyline,yychar,yytext());}
";"  {return new Symbol(sym.ptcoma,yyline,yychar,yytext());}
"%%" {return new Symbol(sym.porcentajed,yyline,yychar,yytext());}

//simbolos expresiones regulares
"."  {return new Symbol(sym.concat,yyline,yychar,yytext());}
"|"  {return new Symbol(sym.or,yyline,yychar,yytext());}
"*"  {return new Symbol(sym.asterisco,yyline,yychar,yytext());}
"+"  {return new Symbol(sym.suma,yyline,yychar,yytext());}
"?"  {return new Symbol(sym.interrogacion,yyline,yychar,yytext());}

"~"  {return new Symbol(sym.guion,yyline,yychar,yytext());}
"," {return new Symbol(sym.coma,yyline,yychar,yytext());}

//FALTA: ver conjunto codigo ascii

\n              {yychar=1;}

//TOKENS PARA EXPRESIONES REGULARES

{BLANCOS}      {}
{D}            {return new Symbol(sym.entero,yyline,yychar,yytext());}
{DD}           {return new Symbol(sym.decimal,yyline,yychar,yytext());}
{CADENA}	      {return new Symbol(sym.cadena,yyline,yychar,yytext());}
{CADENA2}	      {return new Symbol(sym.cadena2,yyline,yychar,yytext());}
{ID}		      {return new Symbol(sym.id,yyline,yychar,yytext());}
{COMENT_M}            {System.out.println("Se ignoro Coment grande");}
{COMENT_L}            {System.out.println("Se ignoro Coment 1 linea");}
{CUALQUIERA}          {return new Symbol(sym.cualquiera,yyline,yychar,yytext());}//<--

//ERRORES LEXICOS , . -> cualquier otra cosa venga 
. {
   
 //   String caracter = yytext();
 //   char car = caracter.charAt(0);
 //   int car_asc = car;
    //33,35-38,40,41,45,47,60-62,64,91,93-95,96,

 //   if(car_asc==33 ||(car_asc>=35 && car_asc<=38) || car_asc==40 || car_asc==41 || car_asc==45 || car_asc==47 ||(car_asc>=60 && car_asc<=62) || car_asc==64 || car_asc==91 ||(car_asc>=93 && car_asc<=95) || car_asc==96){
       //         System.out.println("caracter especial "+yytext());
       //         return new Symbol(sym.simbolo,yyline,yychar,yytext()); //puedo enviar el car_asc de una
       // }else{
            
            System.out.println("Error Léxico--: "+yytext()+", en la linea: "+yyline+", en la columna: "+yychar);
            M_Error er_l = new M_Error("Lexico",yytext(),"El analizador lex no esperaba: "+yytext(),yyline,yychar);
            errores_l.add(er_l);
       // }
   
 }

