#include "com_bi_JniDemo.h"

int number = 0;

/*
* Class:     com_bi_JniDemo
* Method:    set
* Signature: (I)V
*/
JNIEXPORT void JNICALL Java_com_bi_JniDemo_set
(JNIEnv *, jclass, jint i) {
	number = i;
}

/*
* Class:     com_bi_JniDemo
* Method:    get
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_com_bi_JniDemo_get
(JNIEnv *, jclass) {
	return number;
}