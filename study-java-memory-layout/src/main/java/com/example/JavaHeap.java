package com.example;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JavaHeap {
    // Optimizations:
	// -XX:-UseCompressedOops -XX:-UseCompressedClassPointers
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int i= 42; // 4-byte
		Integer j = 42; // 16B + 4B + 4B = 24B without optimizations
		                // 12B + 4B + 0B = 16B with optimizations
		Integer k = Integer.valueOf(42); // Caching , Object Pooling
		Integer x = 42; // auto-boxing
		Integer y = 42;
		//int z = k.intValue(); unboxing
		int z = k; // auto-unboxing
		// x == y == k == j: Only one Integer object exists in the Heap
		var a1 = new A(); // Heap Object -> attribute -> x
		a1 = new A(); // creates garbage
		// new A() -> Eden Space
		// GC moves live objects from Eden , S1 to Survivor Spaces: S0
		// GC moves live objects from Eden , S0 to Survivor Spaces: S1
		// GC moves live objects from Eden , S1 to Survivor Spaces: S0
		// GC moves live objects older than a threshold to Tenure Space
		// SerialGC
		// ParallelGC
		// until Java 8 GCs are not scalable wrt Heap Size
		// G1GC default GC since Java 9 -> incremental + regional
		// Z(ero)GC (Oracle) since Java 16
		// EpsilonGC -> No GC
		// ShannandoahGC (Red Hat) -> Improved Version of G1GC
		// Java Object Layout: jol library -> openjdk
		System.err.println(VM.current().details());
		System.err.println(ClassLayout.parseClass(A.class).toPrintable());
	}

}

// Distributed Cache Server: Hazelcast, Oracle Coherence, ...
class A {
	// Object Header -> 12-byte 
	int x; // 4-byte
	byte b;
	short s;
	long l;
	char c;
	boolean r;
	float f;
	double d;
	B p;
}

class B {} // 12-B + 4-B external