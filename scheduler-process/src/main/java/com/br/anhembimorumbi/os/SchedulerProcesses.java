package com.br.anhembimorumbi.os;

import java.io.StringWriter;
import java.io.Writer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import com.br.anhembimorumbi.os.exceptions.process.IllegalIOTimeException;
import com.br.anhembimorumbi.os.exceptions.IllegalIOTimeEnqueeException;
import com.br.anhembimorumbi.os.exceptions.process.RepeatedProcessEnqueueException;
import com.sun.tools.javac.util.ArrayUtils;
import com.br.anhembimorumbi.os.exceptions.interval.IllegalIntervalException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalIntersecException;
import com.br.anhembimorumbi.os.exceptions.interval.IntervalOutOfOrderException;

public class SchedulerProcesses {
    private static boolean quiet = false;
    private static StringBuilder events = new StringBuilder();
    private static  class Responder implements IProcessListener {
        public Responder(StringBuilder events) {
        }

        public void Quantum(IProcess process) {
            events.append(String.format("#[evento] FIM QUANTUM <%s>\n", process.getShortPID()));
        }

        public void IO(IProcess process) {
            events.append(String.format("#[evento] OPERACAO I/O <%s>\n", process.getShortPID()));
        }

        public void Close(IProcess process) {
            events.append(String.format("#[evento] ENCERRANDO <%s>\n", process.getShortPID()));
        }

        public void Running(IProcess process) {
                //DO NOTHING
        }
    }

    private static void logf(String message, Object... arguments) {
        System.out.println(String.format(message, arguments));
    }

    private static String eventf(String message, Object... arguments) {
        String m = String.format(message, arguments);
        return String.format("#[evento]  %s", m);
    }

    private static void debugf(String message, Object... arguments) {
        if (!quiet) {
            System.out.println(String.format(message, arguments));
        }
    }

    private static void help() {
        System.out.println(String.format("Usage: java -jar SchedulerProcesses.jar [OPTION]... \n"));
        System.out.println("Cria a simulação de um Escalonador do tipo RoundRobin");
        System.out.println("\n");
        System.out.println("Opções que podem ser utilizadas :");
        System.out.println("--file, -f    Arquivo de entrada, com todos os processos, e seus I/O Bounds descritos.");
        System.out.println("--help, -h    Exibe mensagem de help da aplicação.");
        return;
    }

    public static void FILA(IProcess[] process, IProcess current) {
        String elements = "";
        long PID = 0;
        if(current != null) {
            PID = current.PID();
        }
        if(process != null) {
            for (int i = 0; i < process.length; i += 1) {
                if (process[i].isProcessing() || PID == process[i].PID()) {
                    continue;
                }
                elements += String.format(" %s(%d)", process[i].getShortPID(), process[i].getRemaingDuration());
            }
        }

        if (elements.isEmpty()) {
            logf("FILA: Nao ha processos na fila");
            return;
        }
        logf("FILA: %s", elements);
    }

    public static void CPU(IProcess process) {
        String element = String.format(" %s(%d)", process.getShortPID(), process.getRemaingDuration());
        logf("CPU: %s", element);
    }

    public static void main(String[] args) {
        String path = "";
        if (args.length > 0) {
            for (int i = 0; i < args.length; i += 1) {

                switch (args[i]) {
                case "--file":
                case "-f":
                    path = args[i + 1];
                    i += 1;
                    break;
                case "--help":
                case "-h":
                    help();
                    return;
                default:
                    break;
                }
            }
        }

        try {
            File file = new File(path);
            if (!file.exists() || file.isDirectory()) {
                logf("Desculpe, mas o arquivo %s não foi encontrado", path);
                return;
            }

            InputStream inputFile = new FileInputStream(file);
            debugf("Parsing file : " + path);
            IProcessQueue queue = ParseProcess.parse(inputFile);
            if (queue == null || queue.size() == 0) {
                logf("Desculpe, mas o arquivo não possui conteúdo para ser processado, por favor verifique o arquivo.");
            }

            ITime clock = new Time(0);
            IContext c = new Context((Writer) new StringWriter(), 4l, clock);
            RoundRobin scheduler = new RoundRobin(c, 4);
            scheduler.hooks = new Responder(events);

            logf("***********************************");
            logf("***** ESCALONADOR ROUND ROBIN *****");
            while ((!queue.isEmpty() || !scheduler.isEmpty() || events.length() > 0) ) {
                logf("**********  TEMPO %s  **************", clock.get());

                if (queue.size() > 0 && queue.peek().getArrived() == clock.get()) {
                    IProcess process = queue.denqueue();
                    scheduler.add(process);
                    events.append(eventf("CHEGADA <%s>", process.getShortPID()));
                }

                if(events.length() > 0) {
                    logf(events.toString());
                    events.delete(0, events.length());
                }

                clock.add(1);
                if(scheduler.getQueue() != null) {
                    FILA(scheduler.getQueue().toArray(), scheduler.getQueue().peek());
                }

                if (scheduler.size() > 0) {
                    CPU(scheduler.getQueue().peek());
                    scheduler.Run();
                }
            }
            logf("ACABARAM OS PROCESSOS!!!");
            logf("-----------------------------------");
            logf("----- ENCERRANDO SIMULACAO -----");
            logf("-----------------------------------");
        } catch (FileNotFoundException e) {
                logf("Desculpe, mas o arquivo não possui conteúdo para ser processado, por favor verifique o arquivo.");
        } catch (IOException e) {
                logf("Desculpe, ocorreu um erro ao ler o arquivo. Verifique se ele não está corrompido");
        } catch (RepeatedProcessEnqueueException e) {
                logf("Existe processos com o mesmo nome no arquivo, por favor renomear os processos");
        } catch (IllegalIOTimeException e) {
        } catch (IllegalIOTimeEnqueeException e) {
        } catch (IllegalIntervalException e) {
        } catch (IntervalIntersecException e) {
        } catch (IntervalOutOfOrderException e) {
        }
    }
}