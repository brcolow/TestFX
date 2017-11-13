#!/usr/bin/env bash
set -euo pipefail

# vim :set ts=2 sw=2 sts=2 et:
# see http://benlimmer.com/2013/12/26/automatically-publish-javadoc-to-gh-pages-with-travis-ci/ for details

if [ "$TRAVIS_REPO_SLUG" == "TestFX/TestFX" ] && \
   [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && \
   [ "$TRAVIS_PULL_REQUEST" == "false" ] && \
   [ "$TRAVIS_BRANCH" == "master" ]; then
  echo "Publishing Javadoc..."

  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"

  cd "$HOME"
  git clone --quiet --branch=gh-pages https://"${GH_TOKEN}"@github.com/TestFX/TestFX gh-pages > /dev/null

  export projects="testfx-core testfx-junit testfx-junit5 testfx-spock"
  for project in ${projects};
  do
    cp -R "$HOME"/build/TestFX/TestFX/subprojects/"$project"/build/docs/javadoc "$HOME"/javadoc-"$project"
    cd "$HOME"/gh-pages
    git rm -rf "$HOME"/gh-pages/javadoc/"$project"
    cp -Rf "$HOME"/javadoc-"$project" "$HOME"/gh-pages/javadoc
  done
    cd "$HOME"/gh-pages
    git add -f .
    git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages."
    git push -fq origin gh-pages > /dev/null
    echo "Published Javadoc to gh-pages."
fi
