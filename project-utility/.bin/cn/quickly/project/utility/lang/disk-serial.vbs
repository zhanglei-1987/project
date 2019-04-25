Set objFSO = CreateObject("Scripting.FileSystemObject")
Set colDrives = objFSO.Drives
Set objDrive = colDrives.item(Wscript.Arguments(0))
Wscript.Echo objDrive.SerialNumber