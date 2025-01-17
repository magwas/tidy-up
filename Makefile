export MODEL_BASENAME=tidyup
export REPO_NAME=tidy-up
export GITHUB_ORGANIZATION=kode-konveyor
export SONAR_ORG=$(GITHUB_ORGANIZATION)
export LANGUAGE=java

include /usr/local/toolchain/rules.java

all: install shippable/behaviours.xml pmdcheck coveragecheck

shippable/behaviours.xml: tidyup.rich
	zenta-xslt-runner -xsl:xslt/generate_behaviours.xslt -s tidyup.rich modelbasename=tidyup reponame=tidy-up github_org=kode-konveyor

sonar:
	echo "not using sonar"

cpdcheck: javadoc
	if grep -A 1 "<duplication" target/pmd.xml; then exit 1; fi

pmdcheck: javadoc
	if grep -A 1 "<violation" target/pmd.xml; then exit 1; fi

