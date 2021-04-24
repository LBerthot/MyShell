package fr.afpa.cda.main.commandes;

import java.util.Optional;


public class MyTop {

	public static void exec() {
		while (true) {
			try {
				ProcessHandle.allProcesses()
				.forEach(process -> System.out.println(detailsProcessus(process)));
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	private static String detailsProcessus(ProcessHandle process) {
	    return String.format("%8d %8s %10s %26s %-40s",
	            process.pid(),
	            text(process.parent().map(ProcessHandle::pid)),
	            text(process.info().user()),
	            text(process.info().startInstant()),
	            text(process.info().commandLine()));
	}

	private static String text(Optional<?> optional) {
	    return optional.map(Object::toString).orElse("-");
	}

}
