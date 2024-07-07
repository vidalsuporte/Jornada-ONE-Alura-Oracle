 let messagemEntrada;
 let messagemResultado;
let regexMiusculaAcento = /[A-ZÁÉÍÓÚÂÊÎÔÛÃÕÇáàéèíóúãõç]/g;




function criptogravarMsg(){

    messagemEntrada = document.querySelector('textarea').value;
    

    if(regexMiusculaAcento.test(messagemEntrada)){

        alert("Somente permitido letras minúscula e sem acento! Favor digitar novamente!");

     }else{
        messagemEntrada =   messagemEntrada.replace(/e/g, 'enter');
        messagemEntrada =   messagemEntrada.replace(/i/g, 'imes');
        messagemEntrada =   messagemEntrada.replace(/a/g, 'ai');
        messagemEntrada =   messagemEntrada.replace(/o/g, 'ober');
        messagemEntrada =   messagemEntrada.replace(/u/g, 'ufat');
     console.log(messagemEntrada);

        exibirTextoNaTela(messagemEntrada);

     }  
     messagemEntrada ="";
}

function descriptogravarMsg(){

    messagemEntrada = document.querySelector('textarea').value;
    

    if(regexMiusculaAcento.test(messagemEntrada)){

        alert("Somente permitido letras minúscula e sem acento! Favor digitar novamente!");

     }else{
        messagemEntrada =   messagemEntrada.replace(/enter/g, 'e');
        messagemEntrada =   messagemEntrada.replace(/imes/g, 'i');
        messagemEntrada =   messagemEntrada.replace(/ai/g, 'a');
        messagemEntrada =   messagemEntrada.replace(/ober/g, 'o');
        messagemEntrada =   messagemEntrada.replace(/ufat/g, 'u');
     console.log(messagemEntrada);

     exibirTextoNaTela(messagemEntrada);
     messagemEntrada ="";
     }  

}

function exibirTextoNaTela(texto){
    document.getElementById('texto__saida__img').setAttribute('hidden', true);
    document.getElementById('nenhuma_msg').setAttribute('hidden', true);
    document.getElementById('copiar').removeAttribute('hidden');
    let campo = document.querySelector('p');
    campo.innerHTML = texto;
    


}

function copiaResultado(){
let reultado = document.getElementById('texto__saida__aviso');
navigator.clipboard.writeText(reultado.innerHTML);
alert('Mensagem copiada com sucesso!')
document.getElementById('texto__saida__img').removeAttribute('hidden'); 
document.getElementById('nenhuma_msg').removeAttribute('hidden');
document.getElementById('copiar').setAttribute('hidden', true);
let campo = document.querySelector('p');
    campo.innerHTML = "Digite um texto que você deseja criptografar ou descriptografar.";

}