package fr.afpa.cda.main.commandes;

import java.io.IOException;
import java.util.Optional;

import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.ReadAllFile;


public class MyTop {

	public static void exec(CommandeLine cmd) {
		if ((!cmd.getOptions().isEmpty()) && cmd.getOptions().contains("-help")) {
			try {
				ReadAllFile.help(cmd);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		//while (true) {
			//try {
				ProcessHandle.allProcesses()
				.forEach(process -> System.out.println(detailsProcessus(process)));
				/*Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
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
