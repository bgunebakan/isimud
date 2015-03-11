#!/bin/bash
# Created by nixCraft - www.cyberciti.biz
IPT="/sbin/iptables"
MOD="/sbin/modprobe"
# set wan interface such as eth1 or ppp0
SHARE_IF="ppp0"
# clean old fw
echo "Clearing old firewall rules..."
iptables -F
iptables -X
iptables -t nat -F
iptables -t nat -X
iptables -t mangle -F
iptables -t mangle -X
iptables -P INPUT ACCEPT
iptables -P OUTPUT ACCEPT
# Get some kernel modules
echo "Loading kernel modules..."
$MOD ip_tables
$MOD iptable_filter
$MOD iptable_nat
$MOD ip_conntrack
$MOD ipt_MASQUERADE
$MOD ip_nat_ftp
$MOD ip_nat_irc
$MOD ip_conntrack_ftp
$MOD ip_conntrack_irc
# Clean old rules if any, rhel specific but above will take care of everything
# service iptables stop
# unlimited traffic via loopback device
$IPT -A INPUT -i lo -j ACCEPT
$IPT -A OUTPUT -o lo -j ACCEPT
echo "Setting ${SHARE_IF} as router interface..."
$IPT --table nat --append POSTROUTING --out-interface ${SHARE_IF} -j MASQUERADE
# Start other custom rules
#$IPT 
# End other custom rules
echo "*** Instructions on TCP/IP On The Windows / Mac / Linux Masqueraded Client ***"
echo "1. Login to your other LAN desktop computers"
echo "2. Open network configuration GUI tool such. Under Windows XP - Click Start, click Control Panel, click Network and Internet Connections, and then click Network Connections"
echo "3. Set DNS (NS1 and NS2) to 208.67.222.222 and 208.67.220.220"
echo "4. Select the 'Gateway' tab in the TCP/IP properties dialog."
echo "5. Enter $(ifconfig ${SHARE_IF} | grep 'inet addr:'| grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $1}') as the default gateway."

