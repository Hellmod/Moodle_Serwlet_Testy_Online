var urodzinyDzien=16;
var urodzinyMiesiac=10;
var urodzinyRok=1997;
var miesiace = ['stycznia', 'lutego', 'marca', 'kwietnia', 'maja', 'czerwca', 'lipca', 'sierpnia',  'września', 'października', 'listopada', 'grudnia' ];	

function funkcje() {
	var news1 = document.getElementById("news1");
	var news2 = document.getElementById("news2");
	news1.innerHTML = powitanie()+"<br/>"+data()+"<br/>";
	news2.innerHTML = dniDoUrodzin();
	zegarek();
	setInterval(zegarek, 1000);
	}

function powitanie() {
	var dzisiaj = new Date();
	var godzina = dzisiaj.getHours();
	if( (godzina<18) && (godzina>6) ) {
		return 'Dzień dobry,';
	} else {
		return 'Dobry wieczór,';
	}
}

function data() {
	var dzisiaj = new Date();
	var dzien =  dzisiaj.getDate();
	var miesiac = miesiace[dzisiaj.getMonth()];
	var rok = dzisiaj.getFullYear();
	return 'dzisiaj jest '+ dzien + ' ' +  miesiac + ' ' + ' '  + rok + ' r.'; 
}

function zegarek() {
	var data= new Date();
	var godzina = data.getHours();
	var minuta = data.getMinutes();
	var sekunda = data.getSeconds();
	if (minuta<10) minuta="0"+minuta;
	if (sekunda<10) sekunda="0"+sekunda;
	var stopka = document.getElementById("stopka");
	stopka.innerHTML="&copy; 2020 DG | "+godzina+":"+minuta+":"+sekunda;
}

function dniDoUrodzin() {
	var czymialwtymroku=false;/*false nie nie miał     true tak mial */
	var dzisiaj = new Date();
	var dzien =  dzisiaj.getDate();
	var miesiac = dzisiaj.getMonth()+1;

	var rok = dzisiaj.getFullYear();

	// if(miesiac==urodzinyMiesiac&&dzien==urodzinyDzien)
	// 	return 'Wszystkiego najelprzgo ! z okazji'+rok-urodzinyRok ;

	if(miesiac>urodzinyMiesiac) { // nie miał
		if (dzien > urodzinyDzien)
			czymialwtymroku = false;
		else
			czymialwtymroku = true;
	}
	else
		czymialwtymroku = false;

	console.log(czymialwtymroku);

	if(czymialwtymroku) {// miał
		var lata = rok - urodzinyRok;
		var zostaloMiesiecy = miesiac - urodzinyMiesiac;

		var zostaloDni = dzien - urodzinyDzien;
		if (zostaloDni < 0)
			zostaloDni = 30 + zostaloDni;
		zostaloDni = zostaloDni + zostaloMiesiecy * 30;


	}
	else {

		var lata = rok - urodzinyRok - 1;
		var zostaloMiesiecy=urodzinyMiesiac-miesiac;
		if(zostaloMiesiecy<0) {
			zostaloMiesiecy = 12 + zostaloMiesiecy;
			zostaloMiesiecy=zostaloMiesiecy+1;
		}
		var zostaloDni=urodzinyDzien-dzien;
		if(zostaloDni<0)
			zostaloDni=30+zostaloDni;

		zostaloDni=zostaloDni+zostaloMiesiecy*30;
	}

	if(czymialwtymroku)
		return 'Autor miał '+lata+' urodziny ' +  zostaloDni+' dni temu. </br> ('+urodzinyDzien+' '+miesiace[urodzinyMiesiac-1]+' '+urodzinyRok+' )' ;
	else
		return 'Autor ma '+lata+' i będzie mieć urodziny za ' +  zostaloDni+' dni.</br> ('+urodzinyDzien+' '+miesiace[urodzinyMiesiac-1]+' '+urodzinyRok+' )' ;
}