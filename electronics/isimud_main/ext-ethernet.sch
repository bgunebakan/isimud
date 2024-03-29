EESchema Schematic File Version 2
LIBS:power
LIBS:device
LIBS:transistors
LIBS:conn
LIBS:linear
LIBS:regul
LIBS:74xx
LIBS:cmos4000
LIBS:adc-dac
LIBS:memory
LIBS:xilinx
LIBS:microcontrollers
LIBS:dsp
LIBS:microchip
LIBS:analog_switches
LIBS:motorola
LIBS:texas
LIBS:intel
LIBS:audio
LIBS:interface
LIBS:digital-audio
LIBS:philips
LIBS:display
LIBS:cypress
LIBS:siliconi
LIBS:opto
LIBS:atmel
LIBS:contrib
LIBS:valves
LIBS:SOM-A20_C
LIBS:pam8403
LIBS:connectors
LIBS:regulators
LIBS:realtek
LIBS:isimud_main-cache
EELAYER 25 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 2 2
Title "Gigabit ethernet"
Date "2016-09-30"
Rev "REV.B"
Comp "Creworker"
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L RTL8211CL-GR U7
U 1 1 57E785C5
P 6300 3600
F 0 "U7" H 7050 5550 60  0000 C CNN
F 1 "RTL8211CL-GR" H 7200 2200 60  0000 C CNN
F 2 "Housings_QFP:LQFP-48_7x7mm_Pitch0.5mm" H 6550 3250 60  0001 C CNN
F 3 "" H 6550 3250 60  0000 C CNN
	1    6300 3600
	1    0    0    -1  
$EndComp
$Comp
L +3.3V #PWR051
U 1 1 57E786C6
P 9400 1350
F 0 "#PWR051" H 9400 1200 50  0001 C CNN
F 1 "+3.3V" H 9400 1490 50  0000 C CNN
F 2 "" H 9400 1350 50  0000 C CNN
F 3 "" H 9400 1350 50  0000 C CNN
	1    9400 1350
	1    0    0    -1  
$EndComp
$Comp
L INDUCTOR_SMALL L11
U 1 1 57E7874D
P 8650 1450
F 0 "L11" H 8650 1550 50  0000 C CNN
F 1 "FB0805/600R/2A" H 8650 1400 50  0000 C CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 8650 1450 50  0001 C CNN
F 3 "" H 8650 1450 50  0000 C CNN
	1    8650 1450
	1    0    0    -1  
$EndComp
$Comp
L C_Small C33
U 1 1 57E787B4
P 8250 1550
F 0 "C33" H 8260 1620 50  0000 L CNN
F 1 "22uF/6.3V" V 8300 1100 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 8250 1550 50  0001 C CNN
F 3 "" H 8250 1550 50  0000 C CNN
	1    8250 1550
	0    1    1    0   
$EndComp
$Comp
L C_Small C34
U 1 1 57E78801
P 9100 1650
F 0 "C34" H 9110 1720 50  0000 L CNN
F 1 "100nF" V 9150 1350 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 9100 1650 50  0001 C CNN
F 3 "" H 9100 1650 50  0000 C CNN
	1    9100 1650
	0    1    1    0   
$EndComp
$Comp
L C_Small C37
U 1 1 57E78828
P 8300 2050
F 0 "C37" H 8310 2120 50  0000 L CNN
F 1 "22uF/6.3V" V 8350 1600 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 8300 2050 50  0001 C CNN
F 3 "" H 8300 2050 50  0000 C CNN
	1    8300 2050
	0    1    1    0   
$EndComp
$Comp
L C_Small C41
U 1 1 57E78852
P 8300 2250
F 0 "C41" H 8310 2320 50  0000 L CNN
F 1 "100nF" V 8350 1950 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 8300 2250 50  0001 C CNN
F 3 "" H 8300 2250 50  0000 C CNN
	1    8300 2250
	0    1    1    0   
$EndComp
$Comp
L C_Small C35
U 1 1 57E78D7B
P 8250 1750
F 0 "C35" H 8260 1820 50  0000 L CNN
F 1 "100nF" V 8300 1450 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 8250 1750 50  0001 C CNN
F 3 "" H 8250 1750 50  0000 C CNN
	1    8250 1750
	0    1    1    0   
$EndComp
$Comp
L INDUCTOR_SMALL L12
U 1 1 57E78E41
P 8650 1950
F 0 "L12" H 8650 2050 50  0000 C CNN
F 1 "FB0805/600R/2A" H 8650 1900 50  0000 C CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 8650 1950 50  0001 C CNN
F 3 "" H 8650 1950 50  0000 C CNN
	1    8650 1950
	1    0    0    -1  
$EndComp
$Comp
L +3.3V #PWR052
U 1 1 57E78F60
P 9250 1950
F 0 "#PWR052" H 9250 1800 50  0001 C CNN
F 1 "+3.3V" H 9250 2090 50  0000 C CNN
F 2 "" H 9250 1950 50  0000 C CNN
F 3 "" H 9250 1950 50  0000 C CNN
	1    9250 1950
	1    0    0    -1  
$EndComp
$Comp
L C_Small C38
U 1 1 57E7900D
P 9100 2150
F 0 "C38" H 9110 2220 50  0000 L CNN
F 1 "100nF" V 9150 1850 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 9100 2150 50  0001 C CNN
F 3 "" H 9100 2150 50  0000 C CNN
	1    9100 2150
	0    1    1    0   
$EndComp
$Comp
L C_Small C43
U 1 1 57E79181
P 9100 2350
F 0 "C43" H 9110 2420 50  0000 L CNN
F 1 "100nF" V 9150 2050 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 9100 2350 50  0001 C CNN
F 3 "" H 9100 2350 50  0000 C CNN
	1    9100 2350
	0    1    1    0   
$EndComp
$Comp
L R R43
U 1 1 57E7923E
P 8300 2500
F 0 "R43" V 8250 2650 50  0000 C CNN
F 1 "10k" V 8300 2500 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 8230 2500 50  0001 C CNN
F 3 "" H 8300 2500 50  0000 C CNN
	1    8300 2500
	0    1    1    0   
$EndComp
$Comp
L R R44
U 1 1 57E792FD
P 8300 2600
F 0 "R44" V 8250 2750 50  0000 C CNN
F 1 "NA" V 8300 2600 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 8230 2600 50  0001 C CNN
F 3 "" H 8300 2600 50  0000 C CNN
	1    8300 2600
	0    1    1    0   
$EndComp
$Comp
L R R57
U 1 1 57E79603
P 8300 4850
F 0 "R57" V 8380 4850 50  0000 C CNN
F 1 "NA" V 8300 4850 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 8230 4850 50  0001 C CNN
F 3 "" H 8300 4850 50  0000 C CNN
	1    8300 4850
	0    1    1    0   
$EndComp
$Comp
L R R63
U 1 1 57E7964A
P 8300 5050
F 0 "R63" V 8380 5050 50  0000 C CNN
F 1 "1k" V 8300 5050 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 8230 5050 50  0001 C CNN
F 3 "" H 8300 5050 50  0000 C CNN
	1    8300 5050
	0    1    1    0   
$EndComp
$Comp
L R R64
U 1 1 57E7969E
P 8300 5200
F 0 "R64" V 8380 5200 50  0000 C CNN
F 1 "NA" V 8300 5200 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 8230 5200 50  0001 C CNN
F 3 "" H 8300 5200 50  0000 C CNN
	1    8300 5200
	0    1    1    0   
$EndComp
$Comp
L C_Small C65
U 1 1 57E797B2
P 9100 4950
F 0 "C65" H 9110 5020 50  0000 L CNN
F 1 "NA" H 9110 4870 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 9100 4950 50  0001 C CNN
F 3 "" H 9100 4950 50  0000 C CNN
	1    9100 4950
	0    1    1    0   
$EndComp
$Comp
L GND #PWR053
U 1 1 57E79A87
P 9400 5100
F 0 "#PWR053" H 9400 4850 50  0001 C CNN
F 1 "GND" H 9400 4950 50  0000 C CNN
F 2 "" H 9400 5100 50  0000 C CNN
F 3 "" H 9400 5100 50  0000 C CNN
	1    9400 5100
	1    0    0    -1  
$EndComp
$Comp
L INDUCTOR_SMALL L13
U 1 1 57E79F63
P 8650 2750
F 0 "L13" H 8650 2850 50  0000 C CNN
F 1 "FB0805/600R/2A" H 8650 2700 50  0000 C CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 8650 2750 50  0001 C CNN
F 3 "" H 8650 2750 50  0000 C CNN
	1    8650 2750
	1    0    0    -1  
$EndComp
$Comp
L +3.3V #PWR054
U 1 1 57E79FDA
P 9200 2750
F 0 "#PWR054" H 9200 2600 50  0001 C CNN
F 1 "+3.3V" H 9200 2890 50  0000 C CNN
F 2 "" H 9200 2750 50  0000 C CNN
F 3 "" H 9200 2750 50  0000 C CNN
	1    9200 2750
	1    0    0    -1  
$EndComp
$Comp
L C_Small C48
U 1 1 57E7A140
P 8300 2850
F 0 "C48" H 8310 2920 50  0000 L CNN
F 1 "22uF/6.3V" V 8250 2400 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 8300 2850 50  0001 C CNN
F 3 "" H 8300 2850 50  0000 C CNN
	1    8300 2850
	0    1    1    0   
$EndComp
$Comp
L C_Small C51
U 1 1 57E7A2FE
P 8300 3000
F 0 "C51" H 8310 3070 50  0000 L CNN
F 1 "100nF" V 8350 2700 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 8300 3000 50  0001 C CNN
F 3 "" H 8300 3000 50  0000 C CNN
	1    8300 3000
	0    1    1    0   
$EndComp
$Comp
L C_Small C50
U 1 1 57E7A36C
P 9100 2950
F 0 "C50" H 9110 3020 50  0000 L CNN
F 1 "100nF" V 9150 2650 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 9100 2950 50  0001 C CNN
F 3 "" H 9100 2950 50  0000 C CNN
	1    9100 2950
	0    1    1    0   
$EndComp
$Comp
L INDUCTOR_SMALL L14
U 1 1 57E7A564
P 8600 3150
F 0 "L14" H 8600 3250 50  0000 C CNN
F 1 "4.7uH/1.15A/DCR<0.1R/CD43" H 8250 3100 50  0000 C CNN
F 2 "Inductors:Inductor_1212" H 8600 3150 50  0001 C CNN
F 3 "" H 8600 3150 50  0000 C CNN
	1    8600 3150
	1    0    0    -1  
$EndComp
$Comp
L C_Small C52
U 1 1 57E7A641
P 9100 3150
F 0 "C52" H 9110 3220 50  0000 L CNN
F 1 "22uF/6.3V" V 9050 3250 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 9100 3150 50  0001 C CNN
F 3 "" H 9100 3150 50  0000 C CNN
	1    9100 3150
	0    1    1    0   
$EndComp
$Comp
L C_Small C53
U 1 1 57E7A7B3
P 8300 3300
F 0 "C53" H 8310 3370 50  0000 L CNN
F 1 "22uF/6.3V" V 8350 2850 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 8300 3300 50  0001 C CNN
F 3 "" H 8300 3300 50  0000 C CNN
	1    8300 3300
	0    1    1    0   
$EndComp
$Comp
L C_Small C55
U 1 1 57E7AACB
P 8300 3500
F 0 "C55" H 8310 3570 50  0000 L CNN
F 1 "100nF" V 8350 3200 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 8300 3500 50  0001 C CNN
F 3 "" H 8300 3500 50  0000 C CNN
	1    8300 3500
	0    1    1    0   
$EndComp
$Comp
L C_Small C57
U 1 1 57E7AB4C
P 8300 3700
F 0 "C57" H 8310 3770 50  0000 L CNN
F 1 "100nF" V 8350 3400 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 8300 3700 50  0001 C CNN
F 3 "" H 8300 3700 50  0000 C CNN
	1    8300 3700
	0    1    1    0   
$EndComp
$Comp
L C_Small C54
U 1 1 57E7AD0B
P 9100 3400
F 0 "C54" H 9110 3470 50  0000 L CNN
F 1 "100nF" V 9150 3100 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 9100 3400 50  0001 C CNN
F 3 "" H 9100 3400 50  0000 C CNN
	1    9100 3400
	0    1    1    0   
$EndComp
$Comp
L C_Small C56
U 1 1 57E7AD8E
P 9100 3600
F 0 "C56" H 9110 3670 50  0000 L CNN
F 1 "100nF" V 9150 3300 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 9100 3600 50  0001 C CNN
F 3 "" H 9100 3600 50  0000 C CNN
	1    9100 3600
	0    1    1    0   
$EndComp
$Comp
L C_Small C58
U 1 1 57E7ADF6
P 9100 3800
F 0 "C58" H 9110 3870 50  0000 L CNN
F 1 "100nF" V 9150 3500 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 9100 3800 50  0001 C CNN
F 3 "" H 9100 3800 50  0000 C CNN
	1    9100 3800
	0    1    1    0   
$EndComp
$Comp
L R R52
U 1 1 57E7B3FA
P 8150 3900
F 0 "R52" V 8230 3900 50  0000 C CNN
F 1 "2.49k/1%" V 8150 3900 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 8080 3900 50  0001 C CNN
F 3 "" H 8150 3900 50  0000 C CNN
	1    8150 3900
	0    1    1    0   
$EndComp
$Comp
L Crystal_Small Q1
U 1 1 57E7B5B0
P 8600 4100
F 0 "Q1" H 8600 4200 50  0000 C CNN
F 1 "E4SB25.000F20E15(25MHz/20pF/20ppm/5x3.2mm)" V 8650 5250 50  0000 C CNN
F 2 "Capacitors_SMD:C_2220_HandSoldering" H 8600 4100 50  0001 C CNN
F 3 "" H 8600 4100 50  0000 C CNN
	1    8600 4100
	0    1    1    0   
$EndComp
$Comp
L C_Small C62
U 1 1 57E7B8B2
P 9100 4000
F 0 "C62" H 9110 4070 50  0000 L CNN
F 1 "27pF" V 9150 3750 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 9100 4000 50  0001 C CNN
F 3 "" H 9100 4000 50  0000 C CNN
	1    9100 4000
	0    1    1    0   
$EndComp
$Comp
L C_Small C64
U 1 1 57E7BA0A
P 9100 4200
F 0 "C64" H 9110 4270 50  0000 L CNN
F 1 "27pF" V 9150 3950 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 9100 4200 50  0001 C CNN
F 3 "" H 9100 4200 50  0000 C CNN
	1    9100 4200
	0    1    1    0   
$EndComp
$Comp
L R R65
U 1 1 57E7C4F0
P 4100 1400
F 0 "R65" V 4050 1550 50  0000 C CNN
F 1 "NA(4.7k)" V 4100 1100 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4030 1400 50  0001 C CNN
F 3 "" H 4100 1400 50  0000 C CNN
	1    4100 1400
	0    1    1    0   
$EndComp
$Comp
L R RM5G4
U 1 1 57E7CA3F
P 4100 1500
F 0 "RM5G4" V 4050 1650 50  0000 C CNN
F 1 "RA1206_(4X0603)_4B8_22R" V 4100 1050 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4030 1500 50  0001 C CNN
F 3 "" H 4100 1500 50  0000 C CNN
	1    4100 1500
	0    1    1    0   
$EndComp
$Comp
L R RM4G3
U 1 1 57E7CAB8
P 4100 1600
F 0 "RM4G3" V 4050 1750 50  0000 C CNN
F 1 "R" V 4100 1600 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4030 1600 50  0001 C CNN
F 3 "" H 4100 1600 50  0000 C CNN
	1    4100 1600
	0    1    1    0   
$EndComp
$Comp
L R RM4G2
U 1 1 57E7CB34
P 4100 1700
F 0 "RM4G2" V 4050 1850 50  0000 C CNN
F 1 "R" V 4100 1700 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4030 1700 50  0001 C CNN
F 3 "" H 4100 1700 50  0000 C CNN
	1    4100 1700
	0    1    1    0   
$EndComp
$Comp
L R RM4G1
U 1 1 57E7CBB3
P 4100 1800
F 0 "RM4G1" V 4050 1950 50  0000 C CNN
F 1 "R" V 4100 1800 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4030 1800 50  0001 C CNN
F 3 "" H 4100 1800 50  0000 C CNN
	1    4100 1800
	0    1    1    0   
$EndComp
$Comp
L R RM5G3
U 1 1 57E7CC35
P 4100 1900
F 0 "RM5G3" V 4050 2050 50  0000 C CNN
F 1 "RA1206_(4X0603)_4B8_22R" V 4100 1450 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4030 1900 50  0001 C CNN
F 3 "" H 4100 1900 50  0000 C CNN
	1    4100 1900
	0    1    1    0   
$EndComp
$Comp
L R RM5G2
U 1 1 57E7CCBA
P 4100 2000
F 0 "RM5G2" V 4050 2150 50  0000 C CNN
F 1 "RA1206_(4X0603)_4B8_22R" V 4100 1550 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4030 2000 50  0001 C CNN
F 3 "" H 4100 2000 50  0000 C CNN
	1    4100 2000
	0    1    1    0   
$EndComp
$Comp
L R RM5G1
U 1 1 57E7CD42
P 4100 2100
F 0 "RM5G1" V 4050 2250 50  0000 C CNN
F 1 "RA1206_(4X0603)_4B8_22R" V 4100 1650 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4030 2100 50  0001 C CNN
F 3 "" H 4100 2100 50  0000 C CNN
	1    4100 2100
	0    1    1    0   
$EndComp
$Comp
L R R40
U 1 1 57E7CDCD
P 4100 2200
F 0 "R40" V 4050 2350 50  0000 C CNN
F 1 "10K" V 4100 2200 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4030 2200 50  0001 C CNN
F 3 "" H 4100 2200 50  0000 C CNN
	1    4100 2200
	0    1    1    0   
$EndComp
$Comp
L R R41
U 1 1 57E7CE5B
P 4100 2300
F 0 "R41" V 4050 2450 50  0000 C CNN
F 1 "22R" V 4100 2300 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4030 2300 50  0001 C CNN
F 3 "" H 4100 2300 50  0000 C CNN
	1    4100 2300
	0    1    1    0   
$EndComp
$Comp
L R R42
U 1 1 57E7CEEC
P 4100 2400
F 0 "R42" V 4050 2550 50  0000 C CNN
F 1 "22R" V 4100 2400 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4030 2400 50  0001 C CNN
F 3 "" H 4100 2400 50  0000 C CNN
	1    4100 2400
	0    1    1    0   
$EndComp
$Comp
L GND #PWR055
U 1 1 57E7D2FC
P 3350 1400
F 0 "#PWR055" H 3350 1150 50  0001 C CNN
F 1 "GND" H 3350 1250 50  0000 C CNN
F 2 "" H 3350 1400 50  0000 C CNN
F 3 "" H 3350 1400 50  0000 C CNN
	1    3350 1400
	0    1    1    0   
$EndComp
$Comp
L +3.3V #PWR056
U 1 1 57E7D683
P 3350 1700
F 0 "#PWR056" H 3350 1550 50  0001 C CNN
F 1 "+3.3V" H 3350 1840 50  0000 C CNN
F 2 "" H 3350 1700 50  0000 C CNN
F 3 "" H 3350 1700 50  0000 C CNN
	1    3350 1700
	0    -1   -1   0   
$EndComp
$Comp
L GND #PWR057
U 1 1 57E7DA79
P 3300 2200
F 0 "#PWR057" H 3300 1950 50  0001 C CNN
F 1 "GND" H 3300 2050 50  0000 C CNN
F 2 "" H 3300 2200 50  0000 C CNN
F 3 "" H 3300 2200 50  0000 C CNN
	1    3300 2200
	0    1    1    0   
$EndComp
$Comp
L C_Small C42
U 1 1 57E7E694
P 4550 2450
F 0 "C42" H 4560 2520 50  0000 L CNN
F 1 "NA" H 4560 2370 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 4550 2450 50  0001 C CNN
F 3 "" H 4550 2450 50  0000 C CNN
	1    4550 2450
	0    1    1    0   
$EndComp
$Comp
L GND #PWR058
U 1 1 57E7E7E6
P 4850 2450
F 0 "#PWR058" H 4850 2200 50  0001 C CNN
F 1 "GND" H 4850 2300 50  0000 C CNN
F 2 "" H 4850 2450 50  0000 C CNN
F 3 "" H 4850 2450 50  0000 C CNN
	1    4850 2450
	0    -1   -1   0   
$EndComp
$Comp
L R R66
U 1 1 57E7F929
P 4250 3500
F 0 "R66" V 4330 3500 50  0000 C CNN
F 1 "22R" V 4250 3500 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4180 3500 50  0001 C CNN
F 3 "" H 4250 3500 50  0000 C CNN
	1    4250 3500
	0    1    1    0   
$EndComp
$Comp
L C_Small C59
U 1 1 57E80D2D
P 4550 5900
F 0 "C59" H 4560 5970 50  0000 L CNN
F 1 "100nF" V 4500 5950 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 4550 5900 50  0001 C CNN
F 3 "" H 4550 5900 50  0000 C CNN
	1    4550 5900
	0    1    1    0   
$EndComp
$Comp
L C_Small C61
U 1 1 57E80EA0
P 4550 6050
F 0 "C61" H 4560 6120 50  0000 L CNN
F 1 "100nF" V 4550 6150 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603_HandSoldering" H 4550 6050 50  0001 C CNN
F 3 "" H 4550 6050 50  0000 C CNN
	1    4550 6050
	0    1    1    0   
$EndComp
$Comp
L GND #PWR059
U 1 1 57E81108
P 4300 6100
F 0 "#PWR059" H 4300 5850 50  0001 C CNN
F 1 "GND" H 4300 5950 50  0000 C CNN
F 2 "" H 4300 6100 50  0000 C CNN
F 3 "" H 4300 6100 50  0000 C CNN
	1    4300 6100
	1    0    0    -1  
$EndComp
$Comp
L TM211Q01FM22 J2
U 1 1 57E8409B
P 6300 6000
F 0 "J2" H 6400 6600 50  0000 L CNN
F 1 "TM211Q01FM22" H 6250 6600 50  0000 R CNN
F 2 "rj45:TM211Q01FM22" H 6300 6000 50  0001 C CNN
F 3 "" H 6300 6000 50  0000 C CNN
	1    6300 6000
	1    0    0    -1  
$EndComp
$Comp
L R R60
U 1 1 57E86710
P 3950 5050
F 0 "R60" V 4030 5050 50  0000 C CNN
F 1 "NA" V 3950 5050 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 3880 5050 50  0001 C CNN
F 3 "" H 3950 5050 50  0000 C CNN
	1    3950 5050
	1    0    0    -1  
$EndComp
$Comp
L R R59
U 1 1 57E86A31
P 3950 5550
F 0 "R59" V 4030 5550 50  0000 C CNN
F 1 "4K7" V 3950 5550 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 3880 5550 50  0001 C CNN
F 3 "" H 3950 5550 50  0000 C CNN
	1    3950 5550
	1    0    0    -1  
$EndComp
$Comp
L R R62
U 1 1 57E86BD4
P 3750 5050
F 0 "R62" V 3830 5050 50  0000 C CNN
F 1 "4K7" V 3750 5050 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 3680 5050 50  0001 C CNN
F 3 "" H 3750 5050 50  0000 C CNN
	1    3750 5050
	1    0    0    -1  
$EndComp
$Comp
L +3.3V #PWR060
U 1 1 57E86EF6
P 3750 5400
F 0 "#PWR060" H 3750 5250 50  0001 C CNN
F 1 "+3.3V" H 3750 5540 50  0000 C CNN
F 2 "" H 3750 5400 50  0000 C CNN
F 3 "" H 3750 5400 50  0000 C CNN
	1    3750 5400
	-1   0    0    1   
$EndComp
$Comp
L R R61
U 1 1 57E87485
P 3500 5550
F 0 "R61" V 3580 5550 50  0000 C CNN
F 1 "1K" V 3500 5550 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 3430 5550 50  0001 C CNN
F 3 "" H 3500 5550 50  0000 C CNN
	1    3500 5550
	1    0    0    -1  
$EndComp
$Comp
L C_Small C71
U 1 1 57E879BF
P 3250 5550
F 0 "C71" H 3260 5620 50  0000 L CNN
F 1 "1nF/2kV/X7R/1206" V 3100 5500 50  0000 L CNN
F 2 "Capacitors_SMD:C_1206_HandSoldering" H 3250 5550 50  0001 C CNN
F 3 "" H 3250 5550 50  0000 C CNN
	1    3250 5550
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR061
U 1 1 57E882B6
P 3500 5050
F 0 "#PWR061" H 3500 4800 50  0001 C CNN
F 1 "GND" H 3500 4900 50  0000 C CNN
F 2 "" H 3500 5050 50  0000 C CNN
F 3 "" H 3500 5050 50  0000 C CNN
	1    3500 5050
	-1   0    0    1   
$EndComp
$Comp
L R R47
U 1 1 57E8871F
P 4550 6850
F 0 "R47" V 4630 6850 50  0000 C CNN
F 1 "1K" V 4550 6850 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 4480 6850 50  0001 C CNN
F 3 "" H 4550 6850 50  0000 C CNN
	1    4550 6850
	0    1    1    0   
$EndComp
$Comp
L +3.3V #PWR062
U 1 1 57E888F0
P 3350 6750
F 0 "#PWR062" H 3350 6600 50  0001 C CNN
F 1 "+3.3V" H 3350 6890 50  0000 C CNN
F 2 "" H 3350 6750 50  0000 C CNN
F 3 "" H 3350 6750 50  0000 C CNN
	1    3350 6750
	1    0    0    -1  
$EndComp
$Comp
L R R48
U 1 1 57E88B13
P 3750 6950
F 0 "R48" V 3830 6950 50  0000 C CNN
F 1 "4K7" V 3750 6950 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 3680 6950 50  0001 C CNN
F 3 "" H 3750 6950 50  0000 C CNN
	1    3750 6950
	0    1    1    0   
$EndComp
Wire Wire Line
	7900 1850 7750 1850
Wire Wire Line
	7900 1450 7900 1950
Wire Wire Line
	7900 1950 7750 1950
Wire Wire Line
	7750 2150 9000 2150
Wire Wire Line
	7900 2050 7900 2350
Wire Wire Line
	7750 2350 9000 2350
Wire Wire Line
	7750 2250 8200 2250
Connection ~ 7900 2250
Wire Wire Line
	7750 3300 8200 3300
Wire Wire Line
	7900 3250 7900 3800
Wire Wire Line
	7750 3700 8200 3700
Wire Wire Line
	7750 3600 9000 3600
Connection ~ 7900 3600
Wire Wire Line
	7750 3500 8200 3500
Connection ~ 7900 3500
Wire Wire Line
	7750 3400 9000 3400
Connection ~ 7900 3400
Connection ~ 7900 1850
Wire Wire Line
	7900 1450 8400 1450
Wire Wire Line
	8900 1450 9400 1450
Wire Wire Line
	9400 1450 9400 1350
Wire Wire Line
	8150 1550 7900 1550
Connection ~ 7900 1550
Wire Wire Line
	9400 1550 9400 5100
Wire Wire Line
	9400 1550 8350 1550
Wire Wire Line
	9400 1650 9200 1650
Wire Wire Line
	9000 1650 7900 1650
Connection ~ 7900 1650
Wire Wire Line
	8150 1750 7900 1750
Connection ~ 7900 1750
Wire Wire Line
	9400 1750 8350 1750
Connection ~ 9400 1650
Wire Wire Line
	8400 1950 8150 1950
Wire Wire Line
	8150 1950 8150 2050
Wire Wire Line
	7900 2050 8200 2050
Connection ~ 7900 2150
Connection ~ 8150 2050
Wire Wire Line
	9250 1950 8900 1950
Wire Wire Line
	9400 2050 8400 2050
Connection ~ 9400 1750
Wire Wire Line
	9400 2150 9200 2150
Connection ~ 9400 2050
Wire Wire Line
	9400 2250 8400 2250
Connection ~ 9400 2150
Connection ~ 7900 2350
Wire Wire Line
	9400 2350 9200 2350
Connection ~ 9400 2250
Wire Wire Line
	7750 2600 8150 2600
Wire Wire Line
	7900 2600 7900 2500
Wire Wire Line
	7900 2500 8150 2500
Connection ~ 7900 2600
Wire Wire Line
	9400 2600 8450 2600
Connection ~ 9400 2350
Wire Wire Line
	7750 4850 8150 4850
Wire Wire Line
	7950 4850 7950 5200
Wire Wire Line
	7950 5050 8150 5050
Wire Wire Line
	7950 5200 8150 5200
Connection ~ 7950 5050
Connection ~ 7950 4850
Wire Wire Line
	9000 4950 7950 4950
Connection ~ 7950 4950
Wire Wire Line
	9400 4950 9200 4950
Connection ~ 9400 2600
Connection ~ 9400 4950
Wire Wire Line
	8450 4850 9550 4850
Wire Wire Line
	9550 4850 9550 2500
Wire Wire Line
	9550 2500 8450 2500
Wire Wire Line
	9200 2750 8900 2750
Wire Wire Line
	8400 2750 7900 2750
Wire Wire Line
	7900 2750 7900 3000
Wire Wire Line
	7750 2950 9000 2950
Wire Wire Line
	7750 2850 8200 2850
Connection ~ 7900 2850
Wire Wire Line
	8400 2850 9400 2850
Connection ~ 9400 2850
Connection ~ 7900 2950
Wire Wire Line
	7900 3000 8200 3000
Wire Wire Line
	8400 3000 9400 3000
Connection ~ 9400 3000
Wire Wire Line
	9400 2950 9200 2950
Connection ~ 9400 2950
Wire Wire Line
	8350 3150 7900 3150
Wire Wire Line
	7900 3150 7900 3050
Wire Wire Line
	7900 3050 7750 3050
Wire Wire Line
	8850 3150 9000 3150
Wire Wire Line
	9200 3150 9400 3150
Connection ~ 9400 3150
Connection ~ 7900 3700
Connection ~ 7900 3300
Wire Wire Line
	7900 3800 9000 3800
Wire Wire Line
	9200 3400 9400 3400
Connection ~ 9400 3400
Wire Wire Line
	9200 3600 9400 3600
Connection ~ 9400 3600
Wire Wire Line
	9200 3800 9400 3800
Connection ~ 9400 3800
Wire Wire Line
	8400 3700 9400 3700
Connection ~ 9400 3700
Wire Wire Line
	8400 3500 9400 3500
Connection ~ 9400 3500
Wire Wire Line
	8400 3300 9400 3300
Connection ~ 9400 3300
Wire Wire Line
	8950 3150 8950 3250
Wire Wire Line
	8950 3250 7900 3250
Connection ~ 8950 3150
Wire Wire Line
	8000 3900 7750 3900
Wire Wire Line
	8300 3900 9400 3900
Connection ~ 9400 3900
Wire Wire Line
	7850 4000 9000 4000
Wire Wire Line
	7850 4000 7850 4050
Wire Wire Line
	7850 4050 7750 4050
Wire Wire Line
	7850 4150 7750 4150
Wire Wire Line
	7850 4150 7850 4200
Wire Wire Line
	7850 4200 9000 4200
Connection ~ 8600 4200
Connection ~ 8600 4000
Wire Wire Line
	9200 4000 9400 4000
Connection ~ 9400 4000
Wire Wire Line
	9200 4200 9400 4200
Connection ~ 9400 4200
Wire Wire Line
	7750 4350 9400 4350
Connection ~ 9400 4350
Wire Wire Line
	9400 4450 7750 4450
Connection ~ 9400 4450
Wire Wire Line
	7750 4550 9400 4550
Connection ~ 9400 4550
Wire Wire Line
	9400 4650 7750 4650
Connection ~ 9400 4650
Wire Wire Line
	3350 1400 3950 1400
Wire Wire Line
	3950 1600 3800 1600
Wire Wire Line
	3800 1600 3800 1800
Wire Wire Line
	3800 1800 3950 1800
Wire Wire Line
	3350 1700 3950 1700
Connection ~ 3800 1700
Wire Wire Line
	3300 2200 3950 2200
Wire Wire Line
	4250 1400 4500 1400
Wire Wire Line
	4500 1400 4500 1950
Wire Wire Line
	4500 1600 4250 1600
Wire Wire Line
	4850 1850 4750 1850
Wire Wire Line
	4750 1850 4750 1500
Wire Wire Line
	4750 1500 4250 1500
Wire Wire Line
	4450 2450 4300 2450
Wire Wire Line
	4300 2450 4300 2400
Wire Wire Line
	4250 2400 4450 2400
Wire Wire Line
	4850 2450 4650 2450
Wire Wire Line
	4850 2350 4450 2350
Wire Wire Line
	4450 2350 4450 2400
Connection ~ 4300 2400
Wire Wire Line
	4450 2300 4250 2300
Wire Wire Line
	4450 2200 4450 2300
Wire Wire Line
	4450 2200 4250 2200
Wire Wire Line
	4850 2250 4450 2250
Connection ~ 4450 2250
Wire Wire Line
	4300 2150 4850 2150
Wire Wire Line
	4300 2150 4300 2100
Wire Wire Line
	4300 2100 4250 2100
Wire Wire Line
	4350 2150 4350 1800
Wire Wire Line
	4350 1800 4250 1800
Connection ~ 4350 2150
Wire Wire Line
	4400 2050 4850 2050
Wire Wire Line
	4400 1700 4400 2050
Wire Wire Line
	4400 2000 4250 2000
Wire Wire Line
	4450 1950 4850 1950
Wire Wire Line
	4450 1950 4450 1900
Wire Wire Line
	4450 1900 4250 1900
Connection ~ 4500 1950
Connection ~ 4500 1600
Wire Wire Line
	4250 1700 4400 1700
Connection ~ 4400 2000
Wire Wire Line
	2100 2600 4850 2600
Wire Wire Line
	2100 2800 4850 2800
Wire Wire Line
	2100 2900 4850 2900
Wire Wire Line
	2100 3000 4850 3000
Wire Wire Line
	2100 3100 4850 3100
Wire Wire Line
	2100 3300 4850 3300
Wire Wire Line
	2100 3400 4850 3400
Wire Wire Line
	4400 3500 4850 3500
Wire Wire Line
	2100 3500 4100 3500
Wire Wire Line
	4650 5500 5200 5500
Wire Wire Line
	4650 5500 4650 3700
Wire Wire Line
	4650 3700 4850 3700
Wire Wire Line
	4850 3800 4600 3800
Wire Wire Line
	4600 3800 4600 5600
Wire Wire Line
	4600 5600 5200 5600
Wire Wire Line
	4550 5700 5200 5700
Wire Wire Line
	4550 5700 4550 3900
Wire Wire Line
	4550 3900 4850 3900
Wire Wire Line
	4850 4000 4500 4000
Wire Wire Line
	4500 4000 4500 5800
Wire Wire Line
	4500 5800 5200 5800
Wire Wire Line
	4650 5900 5200 5900
Wire Wire Line
	4700 6000 5200 6000
Wire Wire Line
	4700 6000 4700 6050
Wire Wire Line
	4700 6050 4650 6050
Wire Wire Line
	4300 5900 4300 6100
Wire Wire Line
	4300 5900 4450 5900
Wire Wire Line
	4450 6050 4300 6050
Connection ~ 4300 6050
Wire Wire Line
	5200 6150 4450 6150
Wire Wire Line
	4450 6150 4450 4100
Wire Wire Line
	4450 4100 4850 4100
Wire Wire Line
	4850 4200 4400 4200
Wire Wire Line
	4400 4200 4400 6250
Wire Wire Line
	4400 6250 5200 6250
Wire Wire Line
	5200 6450 4350 6450
Wire Wire Line
	4350 6450 4350 4300
Wire Wire Line
	4350 4300 4850 4300
Wire Wire Line
	4850 4400 4300 4400
Wire Wire Line
	4300 4400 4300 6550
Wire Wire Line
	4300 6550 5200 6550
Wire Wire Line
	4850 4600 4150 4600
Wire Wire Line
	4150 4600 4150 6950
Wire Wire Line
	3900 6950 5200 6950
Wire Wire Line
	3950 6750 5200 6750
Wire Wire Line
	4100 6750 4100 4700
Wire Wire Line
	4100 4700 4850 4700
Wire Wire Line
	3950 4900 3950 4800
Wire Wire Line
	3750 4800 4850 4800
Wire Wire Line
	3950 5200 3950 5400
Wire Wire Line
	3750 4900 3750 4800
Connection ~ 3950 4800
Wire Wire Line
	3750 5400 3750 5200
Wire Wire Line
	3950 5700 3950 6750
Connection ~ 4100 6750
Wire Wire Line
	3500 5050 3500 5400
Wire Wire Line
	3250 5300 3950 5300
Connection ~ 3950 5300
Wire Wire Line
	3500 5700 3500 6650
Wire Wire Line
	3500 6650 5200 6650
Wire Wire Line
	3250 5450 3250 5300
Connection ~ 3500 5300
Wire Wire Line
	3250 5650 3250 7200
Wire Wire Line
	3250 7200 5200 7200
Wire Wire Line
	5200 7100 5050 7100
Wire Wire Line
	5050 7100 5050 7200
Connection ~ 5050 7200
Wire Wire Line
	4700 6850 5200 6850
Wire Wire Line
	3350 6750 3350 6950
Wire Wire Line
	3350 6850 4400 6850
Connection ~ 4150 6950
Wire Wire Line
	3350 6950 3600 6950
Connection ~ 3350 6850
Wire Wire Line
	8450 5050 8800 5050
Wire Wire Line
	8800 5050 8800 5400
Wire Wire Line
	8800 5400 9800 5400
Wire Wire Line
	8450 5200 8750 5200
Wire Wire Line
	8750 5200 8750 5550
Wire Wire Line
	8750 5550 9800 5550
$Comp
L R R49
U 1 1 57E90AAA
P 3800 3750
F 0 "R49" V 3880 3750 50  0000 C CNN
F 1 "1K" V 3800 3750 50  0000 C CNN
F 2 "Resistors_SMD:R_0603_HandSoldering" V 3730 3750 50  0001 C CNN
F 3 "" H 3800 3750 50  0000 C CNN
	1    3800 3750
	1    0    0    -1  
$EndComp
$Comp
L +3.3V #PWR063
U 1 1 57E90B3D
P 3800 4150
F 0 "#PWR063" H 3800 4000 50  0001 C CNN
F 1 "+3.3V" H 3800 4290 50  0000 C CNN
F 2 "" H 3800 4150 50  0000 C CNN
F 3 "" H 3800 4150 50  0000 C CNN
	1    3800 4150
	-1   0    0    1   
$EndComp
Wire Wire Line
	3800 4150 3800 3900
Wire Wire Line
	3800 3600 3800 3400
Connection ~ 3800 3400
Wire Wire Line
	2100 2700 4850 2700
Wire Wire Line
	2100 2400 3950 2400
Wire Wire Line
	2100 2300 3950 2300
Wire Wire Line
	2100 2100 3950 2100
Wire Wire Line
	2100 2000 3950 2000
Wire Wire Line
	2100 1900 3950 1900
Wire Wire Line
	2100 1500 3950 1500
Text GLabel 2100 1500 0    60   Input ~ 0
ERXD0
Text GLabel 2100 1900 0    60   Input ~ 0
ERXD1
Text GLabel 2100 2000 0    60   Input ~ 0
ERXD2
Text GLabel 2100 2100 0    60   Input ~ 0
ERXD3
Text GLabel 2100 2300 0    60   Input ~ 0
ERXDV
Text GLabel 2100 2400 0    60   Input ~ 0
ERXCK
Text GLabel 2100 2600 0    60   Input ~ 0
ETXD0
Text GLabel 2100 2700 0    60   Input ~ 0
ETXD1
Text GLabel 2100 2800 0    60   Input ~ 0
ETXD2
Text GLabel 2100 2900 0    60   Input ~ 0
ETXD3
Text GLabel 2100 3000 0    60   Input ~ 0
ETXEN
Text GLabel 2100 3100 0    60   Input ~ 0
ETXCK
Text GLabel 2100 3300 0    60   Input ~ 0
EMDC
Text GLabel 2100 3400 0    60   Input ~ 0
EMDIO
Text GLabel 2100 3500 0    60   Input ~ 0
CLK125
Text GLabel 9800 5400 2    60   Input ~ 0
RESET_N
Text GLabel 9800 5550 2    60   Input ~ 0
EPHY-RST#
$EndSCHEMATC
