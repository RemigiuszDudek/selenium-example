package prv.remigiuszd.selenium.util

import groovy.util.logging.Slf4j

import java.time.Duration

import static java.time.Duration.ofMillis
import static org.awaitility.Awaitility.await

@Slf4j
class DownloadStorage {
	static final File DOWNLOAD_DIRECTORY = new File("build/e2e-test/Downloads")

	static {
		DOWNLOAD_DIRECTORY.mkdirs()
	}

	static File getFile(String fileName) {
		getFile(fileName, Duration.ofSeconds(0))
	}

	static File getFile(String fileName, Duration waitTimeout) {
		File fileToDownload = new File("$DOWNLOAD_DIRECTORY/$fileName")
		log.info("waiting for file, file=$fileToDownload.absolutePath, timeout=$waitTimeout")
		await("waiting for file to download, fileName=$fileName")
			.atMost(waitTimeout)
			.pollInterval(ofMillis(100))
			.until({
				fileToDownload.exists()
			})
		fileToDownload
	}
}
