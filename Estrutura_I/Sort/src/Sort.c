#include<stdio.h>
#include <stdlib.h>
#include <time.h>


void loadVector (int *vec, int length){
    int i;
    for (i = 0; i < length; i++){
        *(vec + i) = rand();
    }
}

void swap (int *a, int *b) {
    int c;
    c = *a;
    *a = *b;
    *b = c;
}

void swapIfGreater (int *a, int *b) {
    if(*a > *b) swap(a,b);
}

void mergeVectors(int *vec1, int length1, int *vec2, int length2){
    // TODO: Try to optimize it to not use an auxiliary vector, it's possible

    int c1 = 0;
    int c2 = 0;
    int caux = 0;
    int vecaux[length1 + length2];
    while(1){
        if(*(vec1 + c1) <= *(vec2 + c2)){
            vecaux[caux] = *(vec1 + c1);
            c1++;
            caux++;
            if(c1 == length1) break;
        } else {
            vecaux[caux] = *(vec2 + c2);
            c2++;
            caux++;
            if(c2 == length2) break;
        }
    }
    for(c1 = c1; c1 < length1; c1++){
        vecaux[caux] = *(vec1 + c1);
        caux++;
    }
    for(c2 = c2; c2 < length2; c2++){
        vecaux[caux] = *(vec2 + c2);
        caux++;
    }

    caux = 0;
    for(c1 = 0; c1 < length1; c1++){
        *(vec1 + c1) = vecaux[caux++];
    }
    for(c2 = 0; c2 < length2; c2++){
        *(vec2 + c2) = vecaux[caux++];
    }
}

int* sortVector (int *vec, int length){
    if(length <  2) return vec;
    if(length == 2){
        swapIfGreater(vec, (vec + 1));
        return vec;
    }
    int *vec1;
    int *vec2;
    int length1;
    int length2;

    length1 = length/2;
    length2 = length-length1;

    vec1 = sortVector(vec, length1);
    vec2 = sortVector((vec + length1),  length2);

    mergeVectors(vec1, length1, vec2, length2);
    return vec;
}

void sortVector2 (int *vec, int length){
    if(length <  2) return;
    if(length == 2){
        swapIfGreater(vec, (vec + 1));
        return;
    }
    int i;
    for(i = 0; i < (length - length%2);i+=2){
        sortVector2((vec+i), 2);
    }

    int *vec1;
    int *vec2;
    int length1;
    int length2;


    int laux=2;
    while(laux<length){
        for(i=0;i<(length-laux);i+=(laux*2)){
            length1 = laux;
            length2 = laux;
            if((length-(i+laux)) <= laux){
                length2 = (length-(i+laux));
            }
            //printf("%i-%i\n", length, laux*2);
            vec1 = (vec + i);
            vec2 = (vec + i) + length1;
            mergeVectors(vec1, length1, vec2, length2);
            //printVector(vec, length);
        }
        laux*=2;
    }
}

void printVector (int *vec, int length){
    int x;
    printf("vector[%i] = {", length);
    for(x = 0; x < length; x++){
        if(x!=0) printf(",");
        printf("%i", *(vec+x));
    }
    printf("}\n");
}

int sequencialSearch(int *vec, int length, int value){
    int i;
    for(i = 0; i < length;i++){
        if(*(vec+i)==value) return i;
    }
    return -1;
}

int binaryIterativeSearch(int *vec, int length, int value){
    int lenaux = length/2;
    int *vecaux = vec;
    int posaux=0;
    while(1){
        if(*(vecaux+lenaux) == value) return posaux + lenaux;
        if(lenaux <= 0) return -1;
        if(*(vecaux+lenaux) > value){
            lenaux = lenaux/2;
        } else {
            vecaux+=lenaux+1;
            posaux+=lenaux+1;
            lenaux = lenaux - lenaux/2 - 1;
        }
    }
}

int binaryRecursiveSearch(int *vec, int length, int value){
    if(length <= 0) return -1;
    int lenaux = length/2;
    int valaux = *(vec + lenaux);
    int posaux;
    if(valaux == value) return lenaux;
    if(valaux > value){
        return binaryRecursiveSearch(vec, lenaux, value);
    } else {
        posaux = binaryRecursiveSearch((vec+lenaux+1), (length-lenaux)-1, value);
        if(posaux == -1) return -1;
        return lenaux + posaux + 1;
    }
}

#define SEARCHES_TIMES 50000
void runTest(int length){
    int vector[length];
    int i;
    clock_t begin;
    clock_t end;
    double time_spent;
    loadVector(vector, length);
    sortVector(vector, length);
    // Half of executions search a vector elements and the another half search a random value
    begin = clock();
    for (i = 0; i < SEARCHES_TIMES; i++){
        if(i%2 == 0) {
            sequencialSearch(vector, length, vector[rand() % length]);
        } else {
            sequencialSearch(vector, length, rand());
        }
    }
    end = clock();
    time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    printf("sequencialSearch(vector[%i]). Time: %f\n", length, time_spent);
    // Half of executions search a vector elements and the another half search a random value
    begin = clock();
    for (i = 0; i < SEARCHES_TIMES; i++){
        if(i%2 == 0) {
            binaryIterativeSearch(vector, length, vector[rand() % length]);
        } else {
            binaryIterativeSearch(vector, length, rand());
        }
    }
    end = clock();
    time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    printf("binaryIterativeSearch(vector[%i]). Time: %f\n", length, time_spent);
    // Half of executions search a vector elements and the another half search a random value
    begin = clock();
    for (i = 0; i < SEARCHES_TIMES; i++){
        if(i%2 == 0) {
            binaryRecursiveSearch(vector, length, vector[rand() % length]);
        } else {
            binaryRecursiveSearch(vector, length, rand());
        }
    }
    end = clock();
    time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    printf("binaryRecursiveSearch(vector[%i]). Time: %f\n", length, time_spent);
}

void searchTest(){
    printf("\n--------------SEARCH TEST-----------------\n");
    int length = 200000;
    int vector[length];
    int val;
    int i;
    int posrec;
    int posite;
    loadVector(vector, length);
    sortVector(vector, length);

    //printVector(vector, length);

    // Verify if the binary search returns the same result for recursive and iterative searches
    for (i = 0; i < 10; i++){
        if(i%2 == 0) {
            val = vector[rand() % length];
        } else {
            val = rand();
        }

        posrec = binaryRecursiveSearch(vector, length, val);
        posite = binaryIterativeSearch(vector, length, val);

        //printf("val: %i, posrec: %i, posite: %i\n", val, posrec, posite);

        if((posrec != -1 || posite != -1) && (vector[posrec] != vector[posite] || vector[posrec] != val)){
            printf("Binary search error!!!(%i!=%i)\n", vector[posrec], vector[posite]);
        }
    }

}

int binaryRecursiveSearchStack(int *vec, int length, int value){
    printf("binaryRecursiveSearchStack(%d->%i,%i,%i);\n", vec, *vec, length, value);
    if(length <= 0) return -1;
    int lenaux = length/2;
    int valaux = *(vec + lenaux);
    int posaux;
    if(valaux == value) return lenaux;
    if(valaux > value){
        return binaryRecursiveSearchStack(vec, lenaux, value);
    } else {
        posaux = binaryRecursiveSearchStack((vec+lenaux+1), (length-lenaux)-1, value);
        if(posaux == -1) return -1;
        return lenaux + posaux + 1;
    }
}

void stackTest(){
    printf("\n--------------STACK TEST----------------\n");
    int vector[] = {1,20,32,41,58,62,71,88,97,100,112,115,120,180,199};
    binaryRecursiveSearchStack(vector, 15, 65);
}

int main (){
    srand(time(NULL));

    // Run binary search test
    searchTest();

    // Run stack test
    stackTest();

    // Run time test
    printf("\n--------------TIME TEST-----------------\n");
    runTest(10000);
    runTest(50000);
    runTest(100000);
    runTest(200000);


    return 0;
}
