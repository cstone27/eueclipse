# Introduction #

Instructions for installing the editor.


# Details #

  1. Download latest binary
  1. In Eclipse, go to Help -> Install New Software
  1. If you haven't added a software site for the Euphoria editor, add a new one.  Otherwise, ensure that the software site's location is pointing at the downloaded zip file.
  1. Once the repository is added and selected, ensure that Group items by category is **not** checked, otherwise the the editor will not show up in the available software to install.
  1. Select the editor and finish the wizard.  The installer will prompt about installing unsigned content and will likely ask to restart Eclipse once the plug-in is installed.
  1. Once the installation is complete, the syntax coloring for strings needs to be configured manually. This is a known issue and will be fixed in the next release. Go to Window -> Preferences and in the preferences window go to Euphoria -> Editor -> Syntax Coloring and select a color for strings.