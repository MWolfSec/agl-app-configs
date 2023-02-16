SUMMARY = "Flutter Montior"
DESCRIPTION = "A Flutter based IVI Monitoring Dashboard Application for automotive grade Linux."

HOMEPAGE = "https://github.com/MWolfSec/agl-flutter-monitor"

BUGTRACKER = "https://github.com/MWolfSec/agl-flutter-monitor/issues"

SECTION = "graphics"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://License.md;md5=f712ede8d4f845976061925d1416fc40"


SRC_URI = "git://github.com/MWolfSec/agl-flutter-monitor;protocol=https;branch=${AGL_BRANCH} \
    file://MONITOR_config.yaml \
    "

SRCREV = "b5ae61f6229ac92fe4c69e68d4af330f055150ec"
S = "${WORKDIR}/git"

inherit agl-app flutter-app

# flutter-app
#############
PUBSPEC_APPNAME = "flutter_monitor"
FLUTTER_APPLICATION_INSTALL_PREFIX = "/flutter"
FLUTTER_BUILD_ARGS = "bundle -v"

# agl-app
#########
AGL_APP_TEMPLATE = "agl-app-flutter"
AGL_APP_ID = "flutter_monitor"
AGL_APP_NAME = "MONITOR"

do_install:append() {
    install -d ${D}${sysconfdir}/xdg/AGL
    install -m 0644 ${WORKDIR}/MONITOR_config.yaml ${D}${sysconfdir}/xdg/AGL/
}

FILES:${PN} += "${sysconfdir}/xdg/AGL"
