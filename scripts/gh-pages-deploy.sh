#!/bin/bash
git checkout --orphan gh-pages
echo "Building dist..."
./gradlew html:dist
git --work-tree /html/build/dist/ add --all
git --work-tree /html/build/dist/ commit -m gh-pages
echo "Pushing to gh-pages..."
git push origin HEAD:gh-pages --force
rm -r /home/build/dist/
git checkout -f main
git branch -D gh-pages
echo "Successfully deployed..."