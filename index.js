const puppeteer = require("puppeteer");

(async () => {
  const browser = await puppeteer.launch({ headless: false });
  const page = await browser.newPage();
  await page.goto(
    "https://www.woodbrass.com/guitares/guitares+electriques/metal+-+moderne#filtre-liste-categ"
  );

  const esp = await page.evaluate(() => {
    let elements = document.querySelectorAll("div.product_display_gallery");
    let esp = [];

    elements.forEach((element) => {
      const imgFrzLazy = element.querySelector("img.frz-img.lazy");
      if (imgFrzLazy) {
        esp.push({
          type: "image",
          src: imgFrzLazy.src,
        });
      }

      const imgDataHover = imgFrzLazy
        ? imgFrzLazy.getAttribute("data-hover")
        : null;
      if (imgDataHover) {
        esp.push({
          type: "image",
          src: imgDataHover,
        });
      }

      const productName = element.querySelector(
        "p.m0.prod-name.fs12-sm.lh1-0.mt3-sm.fw500-sm"
      );
      if (productName) {
        esp.push({
          type: "productName",
          text: productName.innerText,
        });
      }

      const productBrandOrText = element.querySelector(
        "p.m0.fwb.tup.lh1-0.fs11-sm"
      );
      if (productBrandOrText) {
        esp.push({
          type: "productBrandOrText",
          text: productBrandOrText.innerText,
        });
      }

      const productPrice = element.querySelector(
        "div.flexOrder1.fs28.fs32-md.fwb.wsnw"
      );
      if (productPrice) {
        esp.push({
          type: "productPrice",
          text: productPrice.innerText,
        });
      }
    });

    return esp;
  });

  console.log(esp);

  setTimeout(async () => {
    await browser.close();
  }, 1000);
})();
