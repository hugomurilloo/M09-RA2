Respostes Teòriques:

1. Per què s’atura l’execució al cap d’un temps?

Perquè l'Organitzador fa servir Thread.sleep(10000) i després interromp tots els fils. Sense això, els fils amb bucles infinits s'executarien per sempre.

2. Què passaria amb probabilitats diferents?

Cas 70% reserva - 30% cancel·lar
Les places se esgotarien més ràpid, més fils quedarien esperant.

Cas 30% reserva - 70% cancel·lar
Hi hauria més places disponibles en general, més missatges de cancelació (molts de "reserva inexistent").

Codi modificat:

// Per a 70%-30%
if (probabilitat < 0.7) {
    esdeveniment.ferReserva(this);
} else {
    esdeveniment.cancelaReserva(this);
}

// Per a 30%-70%
if (probabilitat < 0.3) {
    esdeveniment.ferReserva(this);
} else {
    esdeveniment.cancelaReserva(this);
}

3. Per què la llista i no només un comptador?

Perquè necessitem:

Verificar si un assistent específic te reserva

Evitar que un mateix assistent faci múltiples reserves

Cancelar només reserves existents de cada assistent

El comptador només diu quantes places hi ha, no qui les té.