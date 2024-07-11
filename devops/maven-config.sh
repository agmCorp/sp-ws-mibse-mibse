#!/bin/bash

echo '########## Copio settings al home del usuario ###########'
mkdir $HOME/.m2/
cp settings-maven/settings-security.xml $HOME/.m2/
cp settings-maven/settings.xml $HOME/.m2/
cp settings-maven/.mavenrc $HOME/.mavenrc
echo '########## Creo master password ###########'
sed -i "s|<master>NEXUS_MASTER_PASSWORD</master>|<master>$NEXUS_MASTER_PASSWORD</master>|" $HOME/.m2/settings-security.xml
echo '########## Encripto password de publicador antes de configurar ###########'
sed -i "s|<username>USUARIO_AD</username>|<username>$USUARIO_AD</username>|" $HOME/.m2/settings.xml
sed -i "s|<password>PASSWORD_AD</password>|<password>$PASSWORD_AD</password>|" $HOME/.m2/settings.xml
echo '#####################'
exit 0