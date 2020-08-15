class BUILD:
	def __init__(self, version, final):
		self.run(version, final)

	def run(self, version, final):
		import os	
		#os.system("gradlew setupDecompWorkspace --stop && gradlew clean build")
		os.system("gradlew runClient --stop && gradlew clean build")

		import shutil
		try:
			shutil.copyfile("build/libs/client-" + version + "-release.jar", os.getenv("APPDATA") + "\\.minecraft\\mods\\" + final + ".jar")
			os.system("start C:/Users/Public/Desktop/Minecraft_Launcher");
			print("Finished the build.");
		except:
			print("Error in file or project build.");

		import sys
		sys.exit()

BUILD("3.0", "osiris_plus-3.0");