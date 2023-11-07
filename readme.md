# bb-template
A simple wrapper for Selmer templating, by way of Babashka

Hello!  
I use this little Clojure application to start off my component code at work. It consumes the contents of a folder and runs all the files within through a templating system.

The primary goal was to get started writing Clojure, by creating something that would be useful and satisfying for myself.

The script takes only a single input, which is the name of the new component, written as a normal sentence. It will process filenames and contents, so you can create templates like
-  `{{component-name|kebab-case}}.tsx`
-  `{{component-name|kebab-case}}.tests.tsx`
-  etc

It uses Calva, with a couple of extra filters that suit my needs.
`kebab-case` and `pascal-case`. Their names speak for themselves.

Here's an example template:

```ts
// {{component-name|kebab-case}}/{{component-name|kebab-case}}.tsx
export interface Props {
  name: string;
}

export const {{component-name|pascal-case}} = (props: Props) => {
  return (
    <aside>
      {props.name} wrote some good books
    </aside>
  );
};

```

It uses Babashka, so install that before use.  
Then: 
```bash
$ bb template.clj "sidebar widget"
``` 
will output 

```ts
// sidebar-widget/sidebar-widget.tsx
export interface Props {
  name: string;
}

export const SidebarWidget = (props: Props) => {
  return (
    <aside>
      {props.name} wrote some good books
    </aside>
  );
};

```
