package com.teste.lucas

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.teste.lucas")
		.start()
}

