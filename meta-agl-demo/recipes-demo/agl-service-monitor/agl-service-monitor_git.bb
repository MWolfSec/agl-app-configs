SUMMARY     = "Demo Monitor Service Daemon"
DESCRIPTION = "Demo Monitor Service Daemon"
HOMEPAGE    = "https://github.com/MWolfSec/agl-service-monitor"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

DEPENDS = "boost openssl nlohmann-json systemd"

SRC_URI = "git://github.com/MWolfSec/agl-service-monitor;protocol=https;branch=main \
           file://agl-service-monitor.conf \
           file://agl-service-monitor.token \
"
SRCREV  = "8ed3ad06d169b49d5c441b7548444c820ab6f30e"

PV = "2.0+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit meson pkgconfig systemd

SYSTEMD_SERVICE:${PN} = "agl-service-monitor.service"

do_install:append() {
    # Currently using default global client and CA certificates
    # for KUKSA.val SSL, installing app specific ones would go here.

    # VIS authorization token file for KUKSA.val should ideally not
    # be readable by other users, but currently that's not doable
    # until a packaging/sandboxing/MAC scheme is (re)implemented or
    # something like OAuth is plumbed in as an alternative.
    install -d ${D}${sysconfdir}/xdg/AGL/agl-service-monitor
    install -m 0644 ${WORKDIR}/agl-service-monitor.conf ${D}${sysconfdir}/xdg/AGL/
    install -m 0644 ${WORKDIR}/agl-service-monitor.token ${D}${sysconfdir}/xdg/AGL/agl-service-monitor/
}

FILES:${PN} += "${systemd_system_unitdir}"

RDEPENDS:${PN} += "kuksa-val"
