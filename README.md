# Maintenance to UPCH 2020 "legacy pharmacy" software 🛠

  

## Content 📋

-  [Trello](https://trello.com/b/LCEY0tAQ)

-  [Requirements](https://docs.google.com/document/d/1TUnLOxqZ_Zlmv3QmgArtjHjJ0VBsFH5OscPsshVyW7E/edit?usp=sharing)

-  [Technologies](#technologies-)

-  [Get this project](#get-this-project-)

-  [Configurations](#configurations-)

-  [Run](#run-)

-  [Authors](#authors-)

  

# Technologies 🛸

-  [Eclipse Java Developers](https://www.eclipse.org/downloads/packages/)

-  [JavaFX 11](https://openjfx.io/openjfx-docs/)

-  [MySQL](https://www.mysql.com/downloads/)

-  [FXML](https://openjfx.io/javadoc/11/javafx.fxml/javafx/fxml/doc-files/introduction_to_fxml.html)

  

# Get this project 📥

-  [Git](https://git-scm.com/downloads) on your computer.

- In the CMD, locate yourself in the folder that you want to have the project.

- Put the command in the CMD.

	````
	git clone https://github.com/RocioValedoStgo/farmacia_legado.git
	````
  

# Configurations ⚙

#### Check this steps
- Step 1
	- Create a new `User Library` under.
		> Eclipse > Project > Properties > Java Build Path > Libraries > ModulePath > Add Library > User libraries > New
	- Give it a name.
	- Select the new library.
	- Add External JavaFX 11's JAR's.
	- Search and select JavaFX 11's JAR'S.
- Step 2
	- Create a new `variable`.
		> Run > Run configurations > Java App > Main (1) > arguments > Variables > Edit Variables > New
	- Give it a name.
	- Set the value with the environment variable PATH_TO_FX.
	- `Apply and close`.
- Step 3
	- Modify Execution.
		> Run > Run configurations > Java App > Main (1) > arguments > VM arguments
	- Set the value `--module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml`.

#### Or check this
[JavaFX and Eclipse.](https://openjfx.io/openjfx-docs/)

# Run ▶

  #### If the configuration has been correct, press the "Run Main (1)" button and you must start the program. 🤞🏼

# Authors 👨🏼‍💻

- [Salim Vazquez Solis](https://github.com/SalimVazquez)
- [Rocio Valedo Santiago](https://github.com/RocioValedoStgo)
