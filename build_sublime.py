class BUILD:
	def __init__(self, version):
		self.run(version)

	def run(self, version):
		final = version;

		import os	
		#os.system("gradlew setupDecompWorkspace --stop && gradlew clean build")
		os.system("gradlew runClient --stop && gradlew clean build")

		import shutil
		try:
			shutil.copyfile("build/libs/client-" + final + "-release.jar", os.getenv("APPDATA") + "\\.minecraft\\mods\\osiris-" + final + ".jar")
			os.system("start C:/Users/Public/Desktop/Minecraft_Launcher");
			print("Finished the build.");
		except:
			print("Error in file or project build.");

		import sys
		sys.exit()

BUILD("1.6");