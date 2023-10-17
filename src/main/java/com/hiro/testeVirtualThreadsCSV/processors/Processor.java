package com.hiro.testeVirtualThreadsCSV.processors;

import java.time.Duration;

public class Processor {

    public Runnable executar(int processoId) {
        return () -> {
            System.out.println(Thread.currentThread() + " executando processo: " + processoId);

            try {

                Thread.sleep(Duration.ofSeconds(1));

            } catch (InterruptedException e) {

                throw new RuntimeException(e);

            }

            System.out.println(Thread.currentThread() + " processo finalizado: " + processoId);
        };
    }

//    public Runnable executarCSV(String cell, int processoId) {
//        return () -> {
//
//            System.out.println(cell + "\t ");
//            System.out.println(Thread.currentThread());
//            System.out.println("Processo: " + processoId + " finalizado!");
////            System.out.println(Thread.currentThread());
//
//        };
//    }

}
