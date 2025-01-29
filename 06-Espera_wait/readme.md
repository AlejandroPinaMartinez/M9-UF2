# 📌 Preguntes Teòriques

---

## 🕒 1. Per què s'atura l'execució al cap d'un temps?

L'execució es pot aturar perquè els fils assistents poden quedar bloquejats esperant a fer una reserva quan totes les places estan ocupades. Si no hi ha fils que cancel·lin reserves, els altres fils esperaran indefinidament en `wait()`, fent que el programa sembli aturat.

---

## 🎲 2. Canvi de probabilitats

### a) Probabilitat **70%** ferReserva - **30%** cancelaReserva

📌 **Modificació al codi:**

```java
if (random.nextInt(100) < 70) { // 70% fer reserva
    esdeveniment.ferReserva(this);
} else { // 30% cancel·lar reserva
    esdeveniment.cancelaReserva(this);
}
```

**Sortida esperada:**

Amb més reserves que cancel·lacions, l'esdeveniment s'omplirà ràpidament i molts assistents hauran d'esperar per poder reservar.

---

### b) Probabilitat **30%** ferReserva - **70%** cancelaReserva

📌 **Modificació al codi:**

```java
if (random.nextInt(100) < 30) { // 30% fer reserva
    esdeveniment.ferReserva(this);
} else { // 70% cancel·lar reserva
    esdeveniment.cancelaReserva(this);
}
```

**Sortida esperada:**

Hi haurà moltes més cancel·lacions que reserves, de manera que gairebé sempre hi haurà places disponibles i pocs assistents hauran d'esperar per fer una reserva.

---

## 📋 3. Per què fa falta la llista i no només una variable sencera de reserves?

La llista permet saber quins assistents han fet una reserva, cosa que és necessària per gestionar les cancel·lacions. 

Si només tinguéssim un comptador de reserves, no podríem identificar qui ha fet cada reserva ni validar si un assistent pot cancel·lar la seva reserva o no.

