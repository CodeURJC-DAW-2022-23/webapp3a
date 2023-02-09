//el comentario es el mismo que en java o c
//alert("Hola desde script.js");

$(document).ready(function(){
    console.log('Document loaded');   
});

//añadir texto en cuadro de dialogo y mostrar como texto en pantalla --> añadir reseña
$(function(){
    $('#boton').click(function(){
        console.log("click");
        var texto = $('#texto').val();//obtener valor
        $('#texto').val('');//borrar campo
        $('#data').append('<li>' + text + '</li>');//añadir
    });
});

//filtrar elementos usando funciones
$(function(){
    $('a').filter(function(){
        return this.hostname && this.hostname!=location.hostname;
    }).addClass('external');
});

//navegar por el DOM desde un elemento
$(function(){
    $('td:contains(Henry)').next().addClass('highlight');
    $('td:contains(Henry)').parent().find('td:eq(1)')
    .addClass('highlight').end().find('td:eq(2)')
    .addClass('highlight');
});

//eventos
$(function(){
    $('#consola').on('click',function(){
        $(/*this*/'body').addClass('selected');
    });
});

$('#consola').on('click',function(event){
    console.log('Generado en: ' + event.target);
});

//acceso a elementos nativos del DOM
//varmyTag = $('#my-element').get(0).tagName;

//cambiar texto del HTML --> añadir nombre, descripcion segun peli
function cambiaTexto(){
    var divTexto = document.getElementById("texto");
    divTexto.innerHTML = "hola <b>caracola</b>";
    divTexto.style="font-size: 70px";
}

/*function alerta(){
    alert('el boton ha sido pulsado');
}*/

document.addEventListener("DOMContentLoaded",function(){
    //alert('Documento  cargado');
    var element = document.getElementById("consola");
    element.addEventListener("click",function(){
        console.log("pulsado boton consola");
    });
});

console.log('Texto');

if(true){
    console.log("Adios");
}

/*var nombre = "Pepe";
var edad;
var fechaNacimiento = "12/04/1990";
var jefe = true;
var sueldo = 3245;

jefa = false;

console.log("Nombre: " + nombre + " Edad: " + edad + " Jefe: " + jefe);
*/
var jefe = true;

var sueldo = jefe ? 6000 : 5000;

if(jefe){
    sueldo = 4000;
}else{
    sueldo = 5000;
}
console.log(sueldo);
/*
var imprimir = function(param){
    var numero = 0;
    console.log(param + " numero: " + numero);
}

imprimir(4);


(function(){
    var v1;
    var v2;

    var imprimir = function(param){
        var numero = 0;
        console.log(param + " numero: " + numero);
    }
    
    imprimir(4);
}());

var texto = "Hola";

var print_texto = function(){
    console.log(texto);
}

print_texto();

function newImprimirFunction(){

    var texto = "Hola";
    var count = 0;

    var imprimir = function(param){
        var numero = 0;
        console.log("param " + param + " numero:" + numero);
        console.log("salad: " + texto);
        count++;
    }

    return imprimir; 
}

var imprimir = newImprimirFunction();

imprimir(4);
imprimir(5);

texto = "Adios";

imprimir(5);

function alertOnClick(domNode){
    domNode.onClick = function(e){
        alert("Alerta");
    }
}


function divideArrays(numbers){
    var pares = [];
    var impares = [];

    for(var i=0; i<numbers.length; i++){
        var num = numbers[i];
        if(num % 2 == 0){
            pares.push(num);
        }else{
            impares.push(num);
        }
    }
    return [pares,impares];
}

console.log(divideArrays([1,2,3,4,5,6]));
console.log(divideArrays([0,0,0,0]));
console.log(divideArrays([1,1,2,2,2]));

function quitaCeros(numbers){
    var sinCeros = [];

    for(var i=0; i<numbers.length; i++){
        if(numbers[i] != 0){
            sinCeros.push(numbers[i]);
        }
    }
    return sinCeros;
}
*/

/*-------------------------------------------------------------------------
var config = {}
config['nombre'] = 'Pepe domingo';
config['correo'] = 'pepe.perez@blala.com';
config['idioma'] = 'es';

console.log('Nombre: ' + config['nombre']);

for(key in config){
   console.log(key + ': ' + config[key]);
}

//JAVASCRIPT AVANZADO
function Intervalo(limiteInf,limiteSup){
    if(limiteInf > limiteSup){
        throw { error: 'limiteInf should be lower or equal than limiteSup'};
    }
    this.limiteInf=limiteInf
    this.limiteSup=limiteSup
}
//los objetos se crean con la funcion constructora
//el prototipo esta en un atributo de la funcion constructora
Intervalo.prototype.tostring = function(){
    return '[ ' +this.limiteInf + ' , ' + this.limiteSup + ' ]';
}

Intervalo.prototype.incluido = function(num){
    return num >= this.limiteInf && num <= this.limiteSup;
}

var i1 = new Intervalo(0,3);

console.log(i1.tostring());//llama al metodo tostring
//console.log('Intervalo: ' + i1);

console.log(i1.incluido(2));
console.log(i1.incluido(5));
*/
/*-----------------------------------------------------------------------------------
function Empleado(nombre, salario){
    this.nombre = nombre;
    this.salario = salario;
}
//jefe hereda de empleado
Jefe.prototype = Object.create(Empleado.prototype);

function Jefe(nombre, salario, despacho){
    Empleado.call(this,nombre,salario);
    this.despacho = despacho;
}

Jefe.prototype.toString = function(){
    return Empleado.prototype.toString.call(this + "Despacho: " + this.despacho);
}
*/
/*--------------------------------------------------------------------------------
class Figura{

    constructor(color){
        this.color = color;
        
    }

    calcularRatioAreaPerimetro(){
        this.area = this.calculaArea();
        this.perimetro = this.calculaPerimetro();
        this.ratio = this.area / this.perimetro;
    }
    //clases hijas implementan metodos para clacular el area y el perimetro
    
    calculaArea(){
        throw { error: 'calculaArea should be diefined in clases extending Figura'};
    }
    calculaPerimetro(){
        throw { error: 'calculaPerimetro should be diefined in clases extending Figura'};
    }

    toString(){
        return "color: " + this.color + " area: " + this.area + " perimetro: " + this.perimetro + " ratio: " + this.ratio;
    }
}

class Circulo extends Figura{

    constructor(color, radio){
        super(color);
        this.radio = radio;
        //first son's classes execute then the father class execute
        this.calcularRatioAreaPerimetro();
    }

    calculaArea(){
        return Math.PI * this.radio**2;
    }

    calculaPerimetro(){
        return 2* Math.PI * this.radio;
    }

    toString(){
        return "Circulo [ " + super.toString() + ", radio" + this.radio + "]";
    }
}

class Analizador{

    constructor(figuras){
        this.figuras = figuras;
    }

    sumaAreas(){
        let suma = 0;
        for(let f of this.figuras){
            suma += f.area;
        }
        return suma;
    }

    sumaPerimetros(){
        let suma = 0;
        for(let f of this.figuras){
            suma += f.perimetro;
        }
        return suma;
    }

    areaMedia(){
        let suma = this.sumaAreas();
        return suma / this.figuras.length;
    }

    perimetroMedio(){
        let suma = this.sumaPerimetros();
        return suma / this.figuras.length;
    }

    ratioAreaPerimetroMedio(){
        return this.areaMedia() / this.perimetroMedio();
    }
}

let c1 = new Circulo("rojo",2);
let c2 = new Circulo("rojo",4);
let c = new Circulo("rojo",3);

console.log(c);
console.log(c.toString());

let a = new Analizador([c1,c2]);

console.log("area media: " + a.areaMedia());
console.log("perimetro medio: " + a.perimetroMedio());
console.log("ratio area y perimetro medio:" + a.ratioAreaPerimetroMedio());
*/

//PROGRAMACION ASINCRONA -simplifica el codigo cuando se trabaja con comunicaciones con el servidor-
 /*---------------------------------------------------------------------------------------
//hacer peticiones al backend usando el metodo AJAX de la libreria JQUERY
$.ajax({
    url: "http://server.com/results.html",
})
.done(function(data){//funcion(callback) que ejecuta el codigo cuando llegue la respuesta
    console.log("Respuesta recibida");
    console.log("Data:",data);
});
console.log("Peticion enviada");
*/
//si se hacen muchas peticiones -->

//TIPOS CALLBACK
/*--------------------------------------------------------------------------------------
//- continuacion --> callback llamada al terminar, evita el bloqueo del sistema cuando se va a background, devuelve un valor de respuesta
 FileSystem.readFile('/etc/passwd',function(err,data){
    if(err)throw err;
    console.log(data);
 });
 */

 //- handler, se ejecuta cada vez que hay un evento, pueden haber varios handler a la vez y varias veces uno solo
$("#button").on("click",() => {
    console.log("clicked");
});

$("#button").click(() => {
    console.log("clicked");
});

var oReq = new XMLHttpRequest();

oReq.addEventListener("load",function(){
    console.log("Respuesta recibida");
    console.log(this.responseText);
});

oReq.open("GET", "https://example.org/example.txt");
oReq.send();//metodo no bloqueante
console.log("Peticion enviada");
//los eventos se registran con addEventListener()

// XMLHttpRequest() --> objeto de HMTL5 para hacer peticiones AJAX
/*-----------------------------------------------------------------------------
setTimeout(() => {
    alert("Hello");
}, 3000);//configurar un timeotut y pasar una funcion como parametro que se ejecutará cuando pasen 3s
/*---------------------------------------------------------------------------------*/
//usar varios callbacks continuacion, uno detras de otro <-- SOLUCION:: PROMESAS (se dispone de un objeto cunado sea necesario)
/*
asyncFunction(arg1, arg2)
.then(result => {
    console.log(result);
});//el ojeto representa que en el futuro se tendrá un valor, cuando el objeto está disponible se ejecuta el código que se solicitó

//encadenamiento de llamadas diferentes a funciones que son asincronas
getData()
.then(a => {
    return getMoreData(a)
})
.then(b => {
    return getMoreData(b)
})
.catch(e => {
    console.error(e);//tratamiento errores -> si hay error en cualquier llamada se ejecuta el catch
});

//tipos promesa

//- pendiente de recibir un valor o error
//- finalizada: cuando no esta pendiente, o se rechaza o se cumple
//- resuelta: cuando se ha ejecutado el then -se pasa de bg a fg-
*/
/*-------------------------------------------------------------------------------*/
function loadContent(){
    $.ajax({
        url: "http://jsonplaceholder.typicode.com/posts/l",
    })
    .then(response => {
        if(response.status !== 200){
            console.log("Looks like there was a problem. Status Code: " + response.status);
            return;
        }
        response.ajax().done(data => {//funcion(callback) que ejecuta el codigo cuando llegue la respuesta
            console.log("Respuesta recibida");
            console.log("Data:",data);
        }).catch(function(err){
            console.log("AJAX parse Error :-S",err);
        });
    })
    .catch(function(err){
        console.log("Error :-S",err);
    });
    //console.log("Peticion enviada");
}

//metodo que devuelve / implementa una promesa --> metodo que vuelve del backend con algo
//funcion que se quiere que sea ejecutada cuando pase un tiempo, se rechaza reset()
function sleep(millis){
    return new Promise(resolve => {
        //body del resolve
        setTimeout(() => resolve(), millis);
    })
}

sleep(3000).then(() => console.log("He dormido 3s"));






