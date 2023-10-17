package com.hiro.testeVirtualThreadsCSV.processors;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class Processor {

    private final static String CSVFILE = "C:/Users/gabri/Downloads/TesteVirtualThreadsSpringCSV.CSV";

    public Runnable executar(int processoId) {

        return () -> {

//            System.out.println(Thread.currentThread() + " executando processo: " + processoId);

            try (CSVReader reader = new CSVReader(new FileReader(CSVFILE))) {

                Thread.sleep(Duration.ofSeconds(1));

                String[] nextLine;
                boolean skipHeader = true;

                while((nextLine = reader.readNext()) != null) {

                    if(skipHeader) {

                        skipHeader = false;
                        continue;

                    }

                    if(nextLine[0].equals(String.valueOf(processoId))) {

                        System.out.println("Processo: " + processoId + " - CSV: " + nextLine[0]);

                    }

                }

            } catch (IOException | CsvValidationException | InterruptedException e) {

                throw new RuntimeException(e);

            }

//            System.out.println(Thread.currentThread() + " processo finalizado: " + processoId);

        };
    }

}
