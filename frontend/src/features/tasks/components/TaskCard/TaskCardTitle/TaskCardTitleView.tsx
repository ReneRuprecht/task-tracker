interface TaskCardTitleViewProps {
  title: string;
  onStartEdit: () => void;
}
export function TaskCardTitleView({
  title,
  onStartEdit,
}: TaskCardTitleViewProps) {
  return (
    <span onClick={onStartEdit} className="font-medium text-gray-900">
      {title}
    </span>
  );
}
